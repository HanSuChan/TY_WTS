package com.ty.webproject.member.repository;

import com.ty.webproject.member.dto.MemberDTO;

public interface SelectMemberRepository {
    public MemberDTO getFullUserInfo(String id);
    
    public MemberDTO findIdResult(String name, String email);
   
    public MemberDTO findPwResult(String id, String phone);
}
