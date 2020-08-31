package org.globalvox.mws.enums;

public enum WithdrawalType {

	INDIAN("INDIAN"),
	INTERNATIONAL("INTERNATIONAL");
	
	private String name;
	
	WithdrawalType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
