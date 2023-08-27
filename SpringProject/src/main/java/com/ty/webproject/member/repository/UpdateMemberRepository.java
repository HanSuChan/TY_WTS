package com.ty.webproject.member.repository;

import com.ty.webproject.member.dto.MemberDTO;

public interface UpdateMemberRepository {

	public void updateform(String id);

	public void updateform(String id, String userpw1, String userpw2, String name, String regnum1, String regnum2,
			String email, String phone, String sex, int year, int month, int day, String address, String extraAddress,
			String detailAddress);

	MemberDTO getUserInfo(String id);

	void updateUserInfo(MemberDTO memberDTO);

}
