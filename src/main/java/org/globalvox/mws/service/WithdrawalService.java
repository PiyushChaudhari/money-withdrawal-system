package org.globalvox.mws.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NavigableSet;

import org.globalvox.mws.enums.CurrencyType;
import org.globalvox.mws.enums.TransactionType;
import org.globalvox.mws.model.User;
import org.globalvox.mws.model.UserAccount;
import org.globalvox.mws.model.UserTransaction;
import org.globalvox.mws.vo.WithdrawalRequestVO;

public interface WithdrawalService {

	public WithdrawalRequestVO withdraw(WithdrawalRequestVO requestVO,User user,UserAccount userAccount);
	
	public WithdrawalRequestVO getWithdrawalRequest();
	
	public WithdrawalRequestVO validateAmount(WithdrawalRequestVO withdrawalRequestVO);
	
	public List<Integer> amountMultiplication();
	
	public boolean validateMultiplication(BigDecimal amount);
	
	public NavigableSet<BigDecimal> getNote(CurrencyType currencyType);
	
	public default BigDecimal getTotalWithdrawal(UserAccount userAccount) {
		
		BigDecimal totalWithdrawal = new BigDecimal(0);
		
		for(UserTransaction userTransaction : userAccount.getTransaction()) {
			if(TransactionType.DEBIT.equals(userTransaction.getTransactionType())) {
				totalWithdrawal = totalWithdrawal.add(userTransaction.getAmount());
			}
		}
		
		return totalWithdrawal;
	}
	
	public default BigDecimal getMonthlyWithdrawalLimit(User user) {
		return user.getMonthlySalary().multiply(new BigDecimal(50)).divide(new BigDecimal(100));
	}
}

