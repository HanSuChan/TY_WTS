package com.ty.webproject.balance.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ty.webproject.balance.dto.BalanceDTO;
import com.ty.webproject.balance.service.BalanceService;
import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.stock.controller.StockController;

@Controller
public class BalanceController {

	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	@Autowired
	private HttpSession session; // HttpSession 의존성 주입
	
	@Autowired	
	private BalanceService balanceService;

	@RequestMapping(value = "")
	public ModelAndView MemberBalance(@RequestParam Map<String, String> paramMap, Model model) {
		ModelAndView mav = new ModelAndView();

		// 세션에서 로그인된 사용자 정보 가져오기
		MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");
		String member_Id = loggedInUser.getId();

		BalanceDTO balanceDTO = balanceService.getPrivateBalance(member_Id);
		int balance = balanceDTO.getBalance();
		logger.info("balance= " + balance);

		mav.addObject("memberName", loggedInUser.getName()); // 모델에 추가
		mav.addObject("memberBalance", balanceDTO.getBalance());

		mav.setViewName("/account/totalAccount");
		
		

		return mav;
	}
}
