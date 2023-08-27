package com.ty.webproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ty.webproject.member.dto.MemberDTO;



@Mapper
public interface UpdateMemberDAO {

	public void updateform(MemberDTO memberDTO);
	   
	MemberDTO getUserById(String userId);
	    void updateUser(MemberDTO memberDTO);
	    
}
