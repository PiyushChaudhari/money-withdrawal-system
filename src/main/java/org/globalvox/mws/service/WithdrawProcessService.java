package org.globalvox.mws.service;

import org.globalvox.mws.enums.WithdrawalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class WithdrawProcessService {

	@Autowired
	@Qualifier(value="Indian")
	WithdrawalService indianWithdrawalService;
	
	@Autowired
	@Qualifier(value="International")
	WithdrawalService internationalWithdrawalService;
	
	public WithdrawalService getWithdrawalService(WithdrawalType withdrawalType) {
		if(WithdrawalType.INDIAN.equals(withdrawalType))
			return indianWithdrawalService;
		else if(WithdrawalType.INTERNATIONAL.equals(withdrawalType))
			return internationalWithdrawalService;
		else
			return null;
	} 
	
}
