package com.ty.webproject.member.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.webproject.dao.InsertMemberDAO;
import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.member.repository.InsertMemberRepository;

@Service
public class InsertMemberService implements InsertMemberRepository {

	@Autowired
	private InsertMemberDAO insertMemberDAO;
	

	@Override
	public void insertMemberDTO(MemberDTO memberDTO) {
		insertMemberDAO.insertMemberDTO(memberDTO);
	}

	@Override
	public void insertMemberDTO(String id, String userpw1, String userpw2, String name, String regnum1, String regnum2,
			String email, String phone, String sex, int year, int month, int day, String address, String extraAddress,
			String detailAddress) {

	}

	 @Override
	    public boolean isIdDuplicated(String id) {
	        // id 중복 여부 체크 로직
	        return insertMemberDAO.existsById(id);
	}

}
