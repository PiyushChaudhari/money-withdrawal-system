package org.globalvox.mws.controller;

import org.globalvox.mws.enums.WithdrawalType;
import org.globalvox.mws.service.UserService;
import org.globalvox.mws.service.WithdrawProcessService;
import org.globalvox.mws.vo.WithdrawalRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value= {"/account"})
public class AccountController {

	
	public static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
	
	public static final String ACCOUNT_BALANCE = "account/balance";
	public static final String ACCOUNT_AMOUNT = "account/amount";
	public static final String ACCOUNT_SUMMARY = "account/summary";
	public static final String ACCOUNT_NOTE = "account/note";
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WithdrawProcessService withdrawProcessService; 
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView(ACCOUNT_BALANCE);
		mv.addObject("userAccount", userService.getUserAccountDetails());
		return mv;
	}
	
	@RequestMapping(value="/india",method=RequestMethod.GET)
	public ModelAndView india() {
		ModelAndView mv = new ModelAndView(ACCOUNT_AMOUNT);
		mv.addObject("requestVO",withdrawProcessService.getWithdrawalService(WithdrawalType.INDIAN).getWithdrawalRequest());
		return mv;
	}
	
	@RequestMapping(value="/international",method=RequestMethod.GET)
	public ModelAndView international() {
		ModelAndView mv = new ModelAndView(ACCOUNT_AMOUNT);
		mv.addObject("requestVO", withdrawProcessService.getWithdrawalService(WithdrawalType.INTERNATIONAL).getWithdrawalRequest());
		return mv;
	}
	
	@RequestMapping(value="/note",method=RequestMethod.POST)
	public ModelAndView amount(@ModelAttribute("requestVO") WithdrawalRequestVO requestVO) {
		ModelAndView mv = null;
		withdrawProcessService.getWithdrawalService(requestVO.getWithdrawalType()).validateAmount(requestVO);
		if(requestVO.isError()) {
			mv = new ModelAndView(ACCOUNT_AMOUNT);
		}else {
			mv = new ModelAndView(ACCOUNT_NOTE);
		}
		mv.addObject("requestVO", requestVO);
		return mv;
	}
	
	@RequestMapping(value="/withdraw",method=RequestMethod.POST)
	public ModelAndView withdraw(@ModelAttribute("requestVO") WithdrawalRequestVO requestVO) {
		ModelAndView mv = new ModelAndView(ACCOUNT_SUMMARY);
		withdrawProcessService.getWithdrawalService(requestVO.getWithdrawalType()).withdraw(requestVO, userService.getUserDetails(),userService.getUserAccountDetails());
		mv.addObject("msg", requestVO.getErrorMap().get("msg"));
		return mv;
	}
}
