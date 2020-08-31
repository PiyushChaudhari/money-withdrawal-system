package org.globalvox.mws.vo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.globalvox.mws.enums.CurrencyType;
import org.globalvox.mws.enums.WithdrawalType;

public class WithdrawalRequestVO {

	private WithdrawalType withdrawalType;
	private CurrencyType currencyType;
	private String amount;
	private boolean error;
	private Map<String, String> errorMap = new LinkedHashMap<>();
	private Map<BigDecimal, String> noteMap = new LinkedHashMap<>();

	public WithdrawalType getWithdrawalType() {
		return withdrawalType;
	}

	public void setWithdrawalType(WithdrawalType withdrawalType) {
		this.withdrawalType = withdrawalType;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, String> errorMap) {
		this.errorMap = errorMap;
	}

	public Map<BigDecimal, String> getNoteMap() {
		return noteMap;
	}

	public void setNoteMap(Map<BigDecimal, String> noteMap) {
		this.noteMap = noteMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WithdrawalRequestVO [withdrawalType=");
		builder.append(withdrawalType);
		builder.append(", currencyType=");
		builder.append(currencyType);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", error=");
		builder.append(error);
		builder.append(", errorMap=");
		builder.append(errorMap);
		builder.append(", noteMap=");
		builder.append(noteMap);
		builder.append("]");
		return builder.toString();
	}

}
