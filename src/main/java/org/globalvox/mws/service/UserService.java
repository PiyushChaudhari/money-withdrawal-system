package org.globalvox.mws.service;

import java.math.BigDecimal;

import org.globalvox.mws.enums.CurrencyType;
import org.globalvox.mws.enums.TransactionType;
import org.globalvox.mws.model.User;
import org.globalvox.mws.model.UserAccount;
import org.globalvox.mws.model.UserTransaction;
import org.globalvox.mws.vo.UserRegistrationVO;

public interface UserService {

	public User createUser(UserRegistrationVO userRegistrationVO);

	public User getUserDetails();

	public UserAccount getUserAccountDetails();
	
	public UserTransaction createUserTransaction(BigDecimal amount,TransactionType transactionType,CurrencyType currencyType,String remark);
	
	public UserAccount withdrawAmount(BigDecimal amount);
}
