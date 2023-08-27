package com.ty.webproject.member.repository;

import com.ty.webproject.member.dto.MemberDTO;

public interface InsertMemberRepository {

	public void insertMemberDTO(MemberDTO memberDTO);

	public void insertMemberDTO(String id, String userpw1, String userpw2, String name, String regnum1, String regnum2,
			String email, String phone, String sex, int year, int month, int day, String address, String extraAddress,
			String detailAddress);

	boolean isIdDuplicated(String id);

	

	
}
