package com.ty.webproject.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.webproject.dao.SelectMemberDAO;
import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.member.repository.SelectMemberRepository;

@Service
public class SelectMemberService implements SelectMemberRepository {

	@Autowired
	private SelectMemberDAO selectMemberDAO;

	@Override
	public MemberDTO getFullUserInfo(String id) {
		return selectMemberDAO.getFullUserInfo(id);
	}

	@Override
	public MemberDTO findIdResult(String name, String email) {
		return selectMemberDAO.findIdResult(name, email);
	}

	@Override
	public MemberDTO findPwResult(String id, String phone) {

		System.out.println("id=" + id);
		System.out.println("phone=" + phone);
		return selectMemberDAO.findPwResult(id, phone);
	}
}
