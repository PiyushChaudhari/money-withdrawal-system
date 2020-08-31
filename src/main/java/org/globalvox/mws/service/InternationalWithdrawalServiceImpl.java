package org.globalvox.mws.service;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.globalvox.mws.enums.CurrencyType;
import org.globalvox.mws.enums.WithdrawalType;
import org.globalvox.mws.model.User;
import org.globalvox.mws.model.UserAccount;
import org.globalvox.mws.vo.WithdrawalRequestVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value="International")
public class InternationalWithdrawalServiceImpl implements WithdrawalService{

	@Override
	public WithdrawalRequestVO withdraw(WithdrawalRequestVO requestVO,User user, UserAccount userAccount) {
		return null;
	}

	@Override
	public List<Integer> amountMultiplication() {
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(100);
		return list;
	}

	@Override
	public NavigableSet<BigDecimal> getNote(CurrencyType currencyType) {
		NavigableSet<BigDecimal> list = new TreeSet<>();
		if(CurrencyType.USD.equals(currencyType)) {
			list.add(new BigDecimal(10));
			list.add(new BigDecimal(50));
			list.add(new BigDecimal(100));
			list.add(new BigDecimal(500));
		}else if(CurrencyType.GBP.equals(currencyType)) {
			list.add(new BigDecimal(20));
			list.add(new BigDecimal(50));
			list.add(new BigDecimal(500));
			list.add(new BigDecimal(1000));
		}else if(CurrencyType.AUD.equals(currencyType)) {
			list.add(new BigDecimal(100));
			list.add(new BigDecimal(500));
			list.add(new BigDecimal(1000));
		}
		return list;
	}

	@Override
	public WithdrawalRequestVO getWithdrawalRequest() {
		WithdrawalRequestVO requestVO = new WithdrawalRequestVO();
		requestVO.setWithdrawalType(WithdrawalType.INTERNATIONAL);
		return requestVO;
	}

	@Override
	public WithdrawalRequestVO validateAmount(WithdrawalRequestVO withdrawalRequestVO) {
		return null;
	}

	@Override
	public boolean validateMultiplication(BigDecimal amount) {
		return false;
	}
}
