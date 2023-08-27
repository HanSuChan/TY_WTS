package com.ty.webproject.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.member.repository.UpdateMemberRepository;



@Controller
public class UpdateController {

	@Autowired
	private UpdateMemberRepository updateMemberRepository;

	@RequestMapping(value = "/member/updateform")
	public String updateform(Model model, HttpSession session, HttpServletRequest request) {
		MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");

		if (loggedInUser == null) {
			String loggedInUserId = request.getParameter("id");
			
			if (loggedInUserId != null) {
				loggedInUser = updateMemberRepository.getUserInfo(loggedInUserId);
				if (loggedInUser != null) {
					session.setAttribute("loggedInUser", loggedInUser);
				}
			}
		}

		if (loggedInUser != null) {
			model.addAttribute("memberDTO", loggedInUser);
			} else {
			// 로그인하지 않은 상태에서 접근한 경우 처리
		}

		return "member/updateform";
	}

	@RequestMapping(value = "/member/updateresult")
	public String updateresult(@ModelAttribute MemberDTO memberDTO, @RequestParam("userpw1") String userPwInput,
			@RequestParam("userpw2") String userPwConfirmInput, HttpSession session, Model model) {
		if (userPwInput.equals(userPwConfirmInput)) {
			MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");

			if (loggedInUser != null) {
				// 변경 패스워드를 사용자 정보에 저장
				loggedInUser.setUserpw1(userPwInput);

				// 사용자 정보 업데이트
				updateMemberRepository.updateUserInfo(memberDTO);

				model.addAttribute("memberDTO", loggedInUser);
			}

		}
		return "member/updateresult";
	}
}
