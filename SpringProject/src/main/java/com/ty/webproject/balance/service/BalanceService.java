package com.ty.webproject.balance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.webproject.balance.dto.BalanceDTO;
import com.ty.webproject.balance.repository.BalanceRepository;

@Service
public class BalanceService {
	private static final Logger logger = LoggerFactory.getLogger(BalanceService.class);

	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	public BalanceService(BalanceRepository balanceRepository) {
		this.balanceRepository = balanceRepository;
	}

	public BalanceDTO getBalanceData(String loggedIn_id) {
		System.out.println(balanceRepository.selectBalanceData(loggedIn_id).getBalance());
		return balanceRepository.selectBalanceData(loggedIn_id);
	}
	public BalanceDTO getPrivateBalance(String member_Id) {
		logger.info("BalanceService= " + member_Id);
		return balanceRepository.getPrivateBalance(member_Id);		
	}
	
	public void addPrivateBalance(String member_id) {
		
		balanceRepository.addPrivateBalance(member_id);
		
	}

}
