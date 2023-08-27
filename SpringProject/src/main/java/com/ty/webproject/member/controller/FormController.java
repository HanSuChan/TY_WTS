package com.ty.webproject.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ty.webproject.member.dto.MemberDTO;

@Controller
public class FormController {

	// 로그인 폼
	@RequestMapping(value = "/member/loginform")
	public String logintest(Model model, HttpSession session) {

		model.addAttribute("userid", "");

		// 세션으로부터 사용자 정보를 가져오기
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loggedInUser");

		if (memberDTO != null) {
			model.addAttribute("userid", memberDTO.getId());
		}

		return "member/loginform";
	}

}
