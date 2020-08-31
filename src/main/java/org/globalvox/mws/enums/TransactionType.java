package org.globalvox.mws.enums;

public enum TransactionType {

	DEBIT("DEBIT"),
	CREDIT("CREDIT");
	
	private String name;
	
	TransactionType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
