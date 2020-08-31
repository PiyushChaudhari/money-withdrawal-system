package org.globalvox.mws.service;

import java.util.LinkedHashMap;

import java.util.Map;

import org.globalvox.mws.vo.UserRegistrationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

	
	public static final Logger LOG = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	
	@Override
	public UserRegistrationVO validate(UserRegistrationVO userRegistrationVO) {
		Map<String, String> errorMap = new LinkedHashMap<>();
		
		if(!validName(userRegistrationVO.getName())) {
			errorMap.put("name","Name should be alphabate, one space with mininum 5 and maximum 20 character.");
		}
		if(!validEmail(userRegistrationVO.getEmail())) {
			errorMap.put("email","Invalid Email.");
		}
		if(!validMobile(userRegistrationVO.getMobile())) {
			errorMap.put("mobile","Invalid Mobile.");
		}
		if(!validAge(userRegistrationVO.getAge())) {
			errorMap.put("age","Select Age.");
		}
		if(!validGender(userRegistrationVO.getGender())) {
			errorMap.put("gender","Select Gender.");
		}
		if(!validMonthlySalary(userRegistrationVO.getMonthlySalary())) {
			errorMap.put("monthlySalary","Invalid Monthly Salary.");
		}
		
		if(!errorMap.isEmpty()) {
			userRegistrationVO.setError(Boolean.TRUE);
			userRegistrationVO.setErrorMap(errorMap);
		}
		
		return userRegistrationVO;
	}
}
