package org.globalvox.mws.service;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.globalvox.mws.enums.CurrencyType;
import org.globalvox.mws.enums.TransactionType;
import org.globalvox.mws.enums.WithdrawalType;
import org.globalvox.mws.model.User;
import org.globalvox.mws.model.UserAccount;
import org.globalvox.mws.vo.WithdrawalRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Qualifier(value="International")
public class InternationalWithdrawalServiceImpl implements WithdrawalService{

	@Autowired
	UserService userService;
	
	@Override
	public WithdrawalRequestVO withdraw(WithdrawalRequestVO requestVO,User user, UserAccount userAccount) {
		BigDecimal amount = new BigDecimal(0);
		BigDecimal withdrawalFees = new BigDecimal(5);
		BigDecimal debitedAmount = new BigDecimal(0);
		Boolean hasWithdrawalFees = Boolean.FALSE;
		StringBuilder msg = new StringBuilder();
		
		msg.append("Withdraw amount ").append(requestVO.getAmount()).append(" , Note: ");
		
		for (Map.Entry<BigDecimal, String> entry : requestVO.getNoteMap().entrySet()) {
			if(!StringUtils.isEmpty(entry.getValue())) {
				amount = amount.add(entry.getKey().multiply(new BigDecimal(entry.getValue())));
				msg.append(entry.getKey()).append(" X ").append(entry.getValue()).append(" , ");
			}else {
				msg.append(entry.getKey()).append(" X ").append("0").append(" , ");
			}
		}
		
		if(new BigDecimal(requestVO.getAmount()).compareTo(amount) == 0) {
			
			BigDecimal totDebited = getTotalWithdrawal(userAccount);
			BigDecimal currncyAmount = getIndiaInr(requestVO.getCurrencyType(), new BigDecimal(requestVO.getAmount()));
			BigDecimal transactionFees = getTransactionFees(currncyAmount);
			if(totDebited.compareTo(new BigDecimal(0)) == 1) {
				debitedAmount = totDebited.add(currncyAmount).add(withdrawalFees).add(transactionFees);
				hasWithdrawalFees = Boolean.TRUE;
			}else {
				debitedAmount = totDebited.add(currncyAmount).add(transactionFees);
			}
			
			if(getMonthlyWithdrawalLimit(user).compareTo(debitedAmount) == 1) {
				userService.createUserTransaction(currncyAmount, TransactionType.DEBIT, requestVO.getCurrencyType(), msg.toString());
				userService.withdrawAmount(currncyAmount);
				if(Boolean.TRUE.equals(hasWithdrawalFees)) {
					userService.createUserTransaction(withdrawalFees, TransactionType.DEBIT, requestVO.getCurrencyType(), "Withdrawal Fees.");
					userService.withdrawAmount(withdrawalFees);
				}
				
				userService.createUserTransaction(transactionFees, TransactionType.DEBIT, requestVO.getCurrencyType(), "Transaction Fees");
				userService.withdrawAmount(transactionFees);
				requestVO.setError(Boolean.FALSE);
				requestVO.getErrorMap().put("msg", msg.toString());
			}else {
				requestVO.setError(Boolean.TRUE);
				requestVO.getErrorMap().put("msg", "insufficient balance.");	
			}
			
		}else {
			requestVO.setError(Boolean.TRUE);
			requestVO.getErrorMap().put("msg", "Invalid note amount.");
		}
		
		return requestVO;
	}

	@Override
	public List<Integer> amountMultiplication() {
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(10);
		return list;
	}

	@Override
	public NavigableSet<BigDecimal> getNote(CurrencyType currencyType) {
		NavigableSet<BigDecimal> list = new TreeSet<>();
		if(CurrencyType.USD.equals(currencyType)) {
			list.add(new BigDecimal(10));
			list.add(new BigDecimal(50));
			list.add(new BigDecimal(100));
			list.add(new BigDecimal(500));
		}else if(CurrencyType.GBP.equals(currencyType)) {
			list.add(new BigDecimal(20));
			list.add(new BigDecimal(50));
			list.add(new BigDecimal(500));
			list.add(new BigDecimal(1000));
		}else if(CurrencyType.AUD.equals(currencyType)) {
			list.add(new BigDecimal(100));
			list.add(new BigDecimal(500));
			list.add(new BigDecimal(1000));
		}
		return list;
	}

	@Override
	public WithdrawalRequestVO getWithdrawalRequest() {
		WithdrawalRequestVO requestVO = new WithdrawalRequestVO();
		requestVO.setWithdrawalType(WithdrawalType.INTERNATIONAL);
		return requestVO;
	}

	@Override
	public WithdrawalRequestVO validateAmount(WithdrawalRequestVO withdrawalRequestVO) {
		withdrawalRequestVO.getErrorMap().clear();
		withdrawalRequestVO.setError(Boolean.FALSE);
		withdrawalRequestVO.getNoteMap().clear();
		
		if(!Pattern.matches("^\\d{1,10}$", withdrawalRequestVO.getAmount())) {
			withdrawalRequestVO.setError(Boolean.TRUE);
			withdrawalRequestVO.getErrorMap().put("amount","Invalid Amount.");
			return withdrawalRequestVO;
		}
		
		BigDecimal amount = new BigDecimal(withdrawalRequestVO.getAmount());
		
		if(!validateMultiplication(amount)) {
			withdrawalRequestVO.setError(Boolean.TRUE);
			withdrawalRequestVO.getErrorMap().put("amount"," Withdrawal amount should be multiple of "+amountMultiplication().toString());
			return withdrawalRequestVO;
		}
		
		for(BigDecimal noteAmout : getNote(withdrawalRequestVO.getCurrencyType()).headSet(amount,Boolean.TRUE)) {
			withdrawalRequestVO.getNoteMap().put(noteAmout,null);
		}
		
		return withdrawalRequestVO;
	}

	@Override
	public boolean validateMultiplication(BigDecimal amount) {
		List<Integer> list = amountMultiplication();
		Integer multiplicationTotalCount = list.size();
		Integer multiplicationCount = 0;
		if(multiplicationTotalCount > 0) {
			
			for(Integer multiplyer : list) {
				if(amount.intValue() % multiplyer == 0) {
					multiplicationCount = multiplicationCount + 1;
				}
			}
			return multiplicationTotalCount == multiplicationCount ? true : false;
		}else {
			return false;	
		}
	}
	
	private BigDecimal getIndiaInr(CurrencyType currencyType,BigDecimal amount) {
		// As of now set fixed rate
		if(CurrencyType.USD.equals(currencyType)) {
			return new BigDecimal("73.19").multiply(amount);
		}else if(CurrencyType.GBP.equals(currencyType)) {
			return new BigDecimal("97.76").multiply(amount);
		}else if(CurrencyType.AUD.equals(currencyType)) {
			return new BigDecimal("53.92").multiply(amount);
		}		
		return null;
	}
	
	private BigDecimal getTransactionFees(BigDecimal amount) {
		return amount.multiply(new BigDecimal(2)).divide(new BigDecimal(100));
	}
}
