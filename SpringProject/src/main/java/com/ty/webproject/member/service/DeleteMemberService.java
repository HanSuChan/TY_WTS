package com.ty.webproject.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.webproject.dao.DeleteMemberDAO;
import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.member.repository.DeleteMemberRepository;



@Service
public class DeleteMemberService implements DeleteMemberRepository {

    @Autowired
    private DeleteMemberDAO deleteMemberDAO;

    @Override
    public MemberDTO getUserInfo(String id) {
        return deleteMemberDAO.getUserInfo(id);
    }

    @Override
    public boolean deleteUser(String id) {
        int deleteCount = deleteMemberDAO.deleteUser(id);
        return deleteCount > 0;
    }
}
