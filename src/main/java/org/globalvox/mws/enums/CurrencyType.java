package org.globalvox.mws.enums;

public enum CurrencyType {
	
	INR("INR"),
	USD("USD"),
	GBP("GBP"),
	AUD("AUD");
	
	
	private String type;
	
	CurrencyType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public static CurrencyType[]  getInternationalCurrency() {
		return new CurrencyType[]{CurrencyType.USD, CurrencyType.GBP,CurrencyType.AUD};
	}

	
}
