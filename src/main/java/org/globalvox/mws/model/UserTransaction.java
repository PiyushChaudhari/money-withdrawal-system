package org.globalvox.mws.model;

import java.math.BigDecimal;
import java.util.Date;

import org.globalvox.mws.enums.CurrencyType;
import org.globalvox.mws.enums.TransactionType;

public class UserTransaction {

	private Long id;
	private Long accountNumber;
	private BigDecimal amount;
	private TransactionType transactionType;
	private CurrencyType currencyType;
	private Date transactionDate;
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public CurrencyType getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserTransaction [id=");
		builder.append(id);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", transactionType=");
		builder.append(transactionType);
		builder.append(", currencyType=");
		builder.append(currencyType);
		builder.append(", transactionDate=");
		builder.append(transactionDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}

	
}
