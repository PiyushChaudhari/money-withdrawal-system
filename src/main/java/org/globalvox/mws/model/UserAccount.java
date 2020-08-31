package org.globalvox.mws.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserAccount {

	private Long accountNumber;
	private BigDecimal balance;
	private List<UserTransaction> transaction = new ArrayList<>();

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public void addTransaction(UserTransaction userTransaction) {
		this.transaction.add(userTransaction);
	}

	public List<UserTransaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<UserTransaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAccount [accountNumber=");
		builder.append(accountNumber);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", transaction=");
		builder.append(transaction);
		builder.append("]");
		return builder.toString();
	}

}
