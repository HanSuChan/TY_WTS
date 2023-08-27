package com.ty.webproject.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.member.repository.SelectMemberRepository;



@Controller
public class SelectController {

	@Autowired
	private SelectMemberRepository selectMemberRepository;

	@RequestMapping(value = "/home/index")
	public String selectUserById(@RequestParam("id") String id, @RequestParam("userpw1") String userpw1,
			HttpSession session, Model model) {

		MemberDTO memberDTO = selectMemberRepository.getFullUserInfo(id);

		if (memberDTO != null && memberDTO.getUserpw1().equals(userpw1)) {
			// 로그인 성공
			session.setAttribute("loggedInUser", memberDTO); // 세션에 사용자 정보 저장
	
			// 사용자 정보를 세션에 저장
			MemberDTO fullUserInfo = selectMemberRepository.getFullUserInfo(id);
			session.setAttribute("fullUserInfo", fullUserInfo);

			// 세션에 member_id도 저장
	        session.setAttribute("member_id", memberDTO.getMember_id());
				
			System.out.println("로그인 성공");
			return "forward:/home"; // 로그인 결과 화면을 보여주는 뷰 이름
		} else {
	
			return "member/loginform"; // 로그인 폼 화면으로 리다이렉트
		}
	}

	@RequestMapping(value = "/member/logindata")
	public String viewUserInfo(HttpSession session, Model model) {
		MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");

	    if (loggedInUser != null) {
	        model.addAttribute("loggedInUser", loggedInUser);
	        return "member/logindata"; // 사용자 정보 출력 페이지로 이동
	    } else {
	        return "redirect:/member/loginform"; // 로그인 폼으로 이동
	    }
	}
	// 아이디찾기 결과창
		@RequestMapping(value = "/member/findidresult")
		public String findIdResult(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {

			System.out.println("name: " + name);
			System.out.println("email: " + email);

			MemberDTO memberDTO = selectMemberRepository.findIdResult(name, email);

			if (memberDTO != null) {
				model.addAttribute("idresult", memberDTO.getId()); // Assuming the UserDTO has getId() method
				return "member/findidresult";
			} else {

				return "redirect:/member/findid";
				// Redirect back to findidform with error message
			}
		}

		// 비밀번호찾기 결과창
		@RequestMapping(value = "/member/findpwresult")
		public String findPwResult(@RequestParam("id") String id, @RequestParam("phone") String phone, Model model) {

			System.out.println("id: " + id);
			System.out.println("phone: " + phone);

			MemberDTO memberDTO = selectMemberRepository.findPwResult(id, phone);

			if (memberDTO != null) {
				model.addAttribute("pwresult", memberDTO.getUserpw1()); // Assuming the UserDTO has getId() method
				return "member/findpwresult";
			} else {

				return "redirect:/member/findpw";
				// Redirect back to findidform with error message
			}
		}
}
