package com.ty.webproject.balance.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ty.webproject.balance.dto.BalanceDTO;

@Mapper
public interface BalanceRepository {
	public BalanceDTO selectBalanceData(String member_id);
	
	public BalanceDTO getPrivateBalance(String member_Id);
	
	public void updateBalanceData(BalanceDTO balanceDTO);
	
	public void addPrivateBalance(String member_id);
}
