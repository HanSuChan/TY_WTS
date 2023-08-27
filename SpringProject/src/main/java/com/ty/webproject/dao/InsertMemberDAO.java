package com.ty.webproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ty.webproject.member.dto.MemberDTO;



@Mapper
public interface InsertMemberDAO {

	public void insertMemberDTO(MemberDTO memberDTO);
	
	 boolean existsById(String id);
	
}
