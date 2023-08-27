package com.ty.webproject.member.repository;

import com.ty.webproject.member.dto.MemberDTO;

public interface DeleteMemberRepository {
	MemberDTO getUserInfo(String id);
    boolean deleteUser(String id);
}
