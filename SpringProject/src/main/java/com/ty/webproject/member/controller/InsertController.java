package com.ty.webproject.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.webproject.balance.service.BalanceService;
import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.member.repository.InsertMemberRepository;

@Controller
public class InsertController {

	@Autowired
	private InsertMemberRepository insertMemberRepository;
	@Autowired
	private BalanceService balanceService;
	// 회원가입

	@RequestMapping(value = "/member/membership")
	public String member() {
			return "member/membership";
	}

	@ResponseBody
	@RequestMapping(value = "/member/checkDuplicate")
	public Map<String, Boolean> checkDuplicate(@RequestParam("id") String id) {
		boolean isDuplicate = insertMemberRepository.isIdDuplicated(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("duplicate", isDuplicate);
		return response;
	}

	// id 찾기 폼
	@RequestMapping(value = "/member/findid")
	public String findid() {
		return "member/findid";
	}

	// pw 찾기 폼
	@RequestMapping(value = "/member/findpw")
	public String findidpw() {
		return "member/findpw";
	}

	// 로그아웃 폼
	@RequestMapping(value = "/member/logout")
	public String logouttest(HttpSession session) {
		// 세션 무효화 (로그아웃)
		session.invalidate();

		return "redirect:/member/loginform";

	}

	// 회원가입 결과창
	@RequestMapping(value = "/member/insertUserDTO", method = RequestMethod.POST)
	public String insertMemberDTO(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
	  
		// 시퀀스 값을 테이블에 넣기 위한 트리거가 작동하므로,
	    // member_id 값은 이미 할당되어 있습니다.
		
	    // 회원 정보를 데이터베이스에 삽입
	    insertMemberRepository.insertMemberDTO(memberDTO);

	    // 세션에 사용자 정보 저장
	    session.setAttribute("loggedInUser", memberDTO);
	    String member_id = memberDTO.getId();
	    balanceService.addPrivateBalance(member_id);
	    System.out.println("addPrivateBalance= " +member_id);

	    return "home/index";
	}



}
