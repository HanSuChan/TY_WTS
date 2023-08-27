package com.ty.webproject.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.member.repository.DeleteMemberRepository;

import javax.servlet.http.HttpSession;




@Controller
public class DeleteController {

    @Autowired
    private DeleteMemberRepository deleteMemberRepository;

    @RequestMapping(value = "/member/deleteform")
    public String deleteCheck(Model model, HttpSession session) {
    	MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
        	MemberDTO memberDTO = deleteMemberRepository.getUserInfo(loggedInUser.getId());

            if (memberDTO != null) {
                model.addAttribute("memberDTO", memberDTO);
            }
        }

        return "member/deleteform";
    }

    @RequestMapping(value = "/member/deletedata")
    public String delete(Model model, HttpSession session) {
    	MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            if (deleteMemberRepository.deleteUser(loggedInUser.getId())) {
                session.invalidate(); // 로그아웃처럼 세션 무효화
                return "redirect:/member/deletedata/success"; // 삭제 성공 시의 리디렉션 경로
            }
        }

        // 삭제 실패 시에는 현재 페이지에 그대로 머무르도록 설정
        return "member/deleteform";
    }


    @RequestMapping(value = "/member/deletedata/success")
    public String deleteSuccess() {
        return "member/deletedata"; // 삭제 성공 시 보여줄 뷰 이름
    }

}
