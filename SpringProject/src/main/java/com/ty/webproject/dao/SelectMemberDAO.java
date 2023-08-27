package com.ty.webproject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ty.webproject.member.dto.MemberDTO;



@Mapper
public interface SelectMemberDAO {
    
	public MemberDTO getFullUserInfo(@Param("id") String id);

	public MemberDTO findIdResult(@Param("name") String name, @Param("email") String email);
	
	public MemberDTO findPwResult(@Param("id") String id, @Param("phone") String phone);
}
