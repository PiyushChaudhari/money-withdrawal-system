package org.globalvox.mws.controller;



import org.globalvox.mws.model.User;
import org.globalvox.mws.service.UserRegistrationService;
import org.globalvox.mws.service.UserService;
import org.globalvox.mws.vo.UserRegistrationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/user")
public class UserRegistrationController {


	private static final Logger LOG = LoggerFactory.getLogger(UserRegistrationController.class);

	public static final String USER_REGISTRATION = "user-registration/registration";
	public static final String ACCOUNT_NUMBER = "user-registration/account-number";


	@Autowired
	private UserService userService;

	@Autowired
	private UserRegistrationService userRegistrationService;


	@RequestMapping(value= {"/","/registration"},method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView(USER_REGISTRATION);
		mv.addObject("userRegistration", new UserRegistrationVO() );
		System.out.println("called registration page.");
		return mv;
	}

	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public ModelAndView registration(@ModelAttribute("userRegistration") UserRegistrationVO userRegistrationVO) {
		LOG.info("userRegistrationVO:> {} ",userRegistrationVO);

		ModelAndView mv  = null;

		userRegistrationService.validate(userRegistrationVO);

		if(userRegistrationVO.isError()) {

			mv = new ModelAndView(USER_REGISTRATION);
			mv.addObject("userRegistration", userRegistrationVO );

		}else {

			mv = new ModelAndView(ACCOUNT_NUMBER);
			User user = userService.createUser(userRegistrationVO);

			mv.addObject("accountnumber", user.getAccountNumber() );
			mv.addObject("balance", userRegistrationVO.getMonthlySalary() );
		}
		return mv;

	}

	@RequestMapping(value="/accountnumber",method=RequestMethod.POST)
	public ModelAndView accountNumber(@ModelAttribute("accountnumber") String accountnumber,@ModelAttribute("balance") String balance) {
		LOG.info("accountnumber:> {} ,balance:> {} ",accountnumber,balance);
		ModelAndView mv = new ModelAndView(ACCOUNT_NUMBER);
		mv.addObject("accountnumber", accountnumber );
		mv.addObject("balance", balance );
		return mv;
	}
}
