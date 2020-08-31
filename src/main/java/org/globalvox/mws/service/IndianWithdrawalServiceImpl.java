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
@Qualifier(value="Indian")
public class IndianWithdrawalServiceImpl implements WithdrawalService{
	
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
			if(totDebited.compareTo(new BigDecimal(0)) == 1) {
				debitedAmount = totDebited.add(amount).add(withdrawalFees);
				hasWithdrawalFees = Boolean.TRUE;
			}else {
				debitedAmount = debitedAmount.add(amount);
			}
			
			if(getMonthlyWithdrawalLimit(user).compareTo(debitedAmount) == 1) {
				userService.createUserTransaction(amount, TransactionType.DEBIT, requestVO.getCurrencyType(), msg.toString());
				userService.withdrawAmount(amount);
				if(Boolean.TRUE.equals(hasWithdrawalFees)) {
					userService.createUserTransaction(withdrawalFees, TransactionType.DEBIT, requestVO.getCurrencyType(), "Withdrawal Fees.");
					userService.withdrawAmount(withdrawalFees);
				}
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
		list.add(100);
		return list;
	}

	@Override
	public NavigableSet<BigDecimal> getNote(CurrencyType currencyType) {
		NavigableSet<BigDecimal> list = new TreeSet<>();
		list.add(new BigDecimal(50));
		list.add(new BigDecimal(100));
		list.add(new BigDecimal(500));
		list.add(new BigDecimal(2000));		
		
		return list;
	}

	@Override
	public WithdrawalRequestVO getWithdrawalRequest() {
		WithdrawalRequestVO requestVO = new WithdrawalRequestVO();
		requestVO.setWithdrawalType(WithdrawalType.INDIAN);
		requestVO.setCurrencyType(CurrencyType.INR);
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
			withdrawalRequestVO.getErrorMap().put("amount"," Withdrawal amount should be mulple of "+amountMultiplication().toString());
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

}
