package com.ty.webproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ty.webproject.member.dto.MemberDTO;



@Mapper
public interface DeleteMemberDAO {
	MemberDTO getUserInfo(String id);
    int deleteUser(String id);
}
