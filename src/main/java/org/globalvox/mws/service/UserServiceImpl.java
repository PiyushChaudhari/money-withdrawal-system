package org.globalvox.mws.service;

import java.math.BigDecimal;
import java.util.Date;

import java.util.concurrent.atomic.AtomicLong;

import org.globalvox.mws.enums.CurrencyType;
import org.globalvox.mws.enums.TransactionType;
import org.globalvox.mws.model.User;
import org.globalvox.mws.model.UserAccount;
import org.globalvox.mws.model.UserTransaction;
import org.globalvox.mws.vo.UserRegistrationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	User user= new User();
	UserAccount userAccount = new UserAccount();
	Boolean anyWithdrawal = Boolean.FALSE;
	AtomicLong atomicLong = new AtomicLong(1);	
	AtomicLong atomicTransaction = new AtomicLong(500);

	@Override
	public User createUser(UserRegistrationVO userRegistrationVO) {
		fillUserDeatils(userRegistrationVO);
		createUserAccount();
		return user;
	}
	
	private User fillUserDeatils(UserRegistrationVO userRegistrationVO) {
		user.setName(userRegistrationVO.getName());
		user.setEmail(userRegistrationVO.getEmail());
		user.setMobile(userRegistrationVO.getMobile());
		user.setAge(new Integer(userRegistrationVO.getAge()));
		user.setGender(userRegistrationVO.getGender());
		user.setMonthlySalary(new BigDecimal(userRegistrationVO.getMonthlySalary()));
		Long accountNo = atomicLong.getAndIncrement();
		user.setAccountNumber(accountNo);
		return user;
	}
	
	private UserAccount createUserAccount() {
		userAccount.setAccountNumber(user.getAccountNumber());
		userAccount.setBalance(user.getMonthlySalary());
		createUserTransaction(user.getMonthlySalary(), TransactionType.CREDIT,CurrencyType.INR, "Monthly Salary Credited");
		return userAccount;
	}
	
	@Override
	public UserTransaction createUserTransaction(BigDecimal amount,TransactionType transactionType,CurrencyType currencyType,String remark) {
		
		UserTransaction userTransaction = new UserTransaction();
		userTransaction.setId(atomicTransaction.getAndIncrement());
		userTransaction.setAccountNumber(userAccount.getAccountNumber());
		userTransaction.setAmount(amount);
		userTransaction.setTransactionType(transactionType);
		userTransaction.setCurrencyType(currencyType);
		userTransaction.setRemark(remark);
		userTransaction.setTransactionDate(new Date());
		
		userAccount.addTransaction(userTransaction);
		
		return userTransaction;
	}
	
	private BigDecimal getMonthlyWithdrawalLimit() {
		return user.getMonthlySalary().multiply(new BigDecimal(50)).divide(new BigDecimal(100));
	} 

	@Override
	public User getUserDetails() {
		return user;
	}

	@Override
	public UserAccount getUserAccountDetails() {
		return userAccount;
	}

	@Override
	public UserAccount withdrawAmount(BigDecimal amount) {
		
		BigDecimal balance = userAccount.getBalance();
		balance = balance.subtract(amount);
		userAccount.setBalance(balance);
		return userAccount;
	}

	
}
