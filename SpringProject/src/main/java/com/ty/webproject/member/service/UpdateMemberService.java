package com.ty.webproject.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.webproject.dao.UpdateMemberDAO;
import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.member.repository.UpdateMemberRepository;



@Service
public class UpdateMemberService implements UpdateMemberRepository {

	@Autowired
	private UpdateMemberDAO updateMemberDAO;

	@Override
	public void updateform(String id) {
		

	}

	@Override
	public void updateform(String id, String userpw1, String userpw2, String name, String regnum1, String regnum2,
			String email, String phone, String sex, int year, int month, int day, String address, String extraAddress,
			String detailAddress) {
		

	}

	@Override
	public MemberDTO getUserInfo(String id) {
		
		return updateMemberDAO.getUserById(id);
	}

	@Override
	public void updateUserInfo(MemberDTO memberDTO) {
		updateMemberDAO.updateform(memberDTO);
	}

}
