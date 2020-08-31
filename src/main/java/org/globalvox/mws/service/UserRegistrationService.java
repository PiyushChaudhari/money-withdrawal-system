package org.globalvox.mws.service;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.globalvox.mws.vo.UserRegistrationVO;

public interface UserRegistrationService {

	public UserRegistrationVO validate(UserRegistrationVO userRegistrationVO);
	
	public default boolean validName(String name) {
		return Pattern.matches("^[a-zA-Z](\\s?[a-zA-Z]){5,20}$", name);
	}
	
	public default boolean validEmail(String email) {
		return Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", email);
	}
	
	public default boolean validMobile(String mobile) {
		return Pattern.matches("^[2-9]\\d{9}$", mobile);
	}
	
	public default boolean validAge(String age) {
		if(Pattern.matches("^\\d+$", age)) {
			Integer ageValue = new Integer(age);
			return ( ageValue > 10 && ageValue <= 150) ? true : false;
		}else {
			return false;
		}
	}

	public default boolean validGender(String gender) {
		return ("Male".equals(gender) || "Female".equals(gender)) ? true : false;
	}

	public default boolean validMonthlySalary(String monthlySalary) {
		if(Pattern.matches("^\\d{3,10}$", monthlySalary)) {
			return (new BigDecimal(monthlySalary).compareTo(BigDecimal.ZERO) == 1) ? true : false;
		}else {
			return false;
		}
	}
}
