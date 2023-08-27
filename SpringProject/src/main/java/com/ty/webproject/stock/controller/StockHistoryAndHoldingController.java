package com.ty.webproject.stock.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ty.webproject.balance.dto.BalanceDTO;
import com.ty.webproject.balance.service.BalanceService;
import com.ty.webproject.member.dto.MemberDTO;
import com.ty.webproject.stock.dto.StockHistoryDTO;
import com.ty.webproject.stock.dto.StockHoldingDTO;
import com.ty.webproject.stock.service.StockService;
import com.ty.webproject.trading.service.TradingService;

@Controller
public class StockHistoryAndHoldingController {

	private static final Logger logger = LoggerFactory.getLogger(StockHistoryAndHoldingController.class);

	@Autowired
	private HttpSession session; // HttpSession 의존성 주입

	@Autowired
	private StockService stockService;

	@Autowired
	private BalanceService balanceService;

	@RequestMapping(value = "/account")
	public ModelAndView getMemberBalance(@RequestParam Map<String, String> paramMap, Model model) {
		ModelAndView mav = new ModelAndView();

		// 세션에서 로그인된 사용자 정보 가져오기
		MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");
		String member_Id = loggedInUser.getId();

		logger.info("MemberBalance= " + member_Id);

		BalanceDTO balanceDTO = balanceService.getPrivateBalance(member_Id);
		int balance = balanceDTO.getBalance();

		mav.addObject("memberName", loggedInUser.getName()); // 모델에 추가
		mav.addObject("memberBalance", balanceDTO.getBalance());

		List<StockHoldingDTO> stockHoldingList = stockService.getStockHoldoings(member_Id);
		mav.addObject("stockHoldingList", stockHoldingList);

		logger.info("stockHoldingList= " + stockHoldingList);

		List<StockHistoryDTO> stockHistoryList = stockService.getStockHistory(member_Id);

		mav.addObject("stockHistoryList", stockHistoryList);

		mav.setViewName("/account/totalAccount");

		return mav;
	}

	
	@GetMapping(value = "/getUserHoldings")
	public @ResponseBody List<StockHoldingDTO> getUserHoldings(	@RequestParam String loggedInUserId) {
		List<StockHoldingDTO> stockHoldingList = stockService.getStockHoldoings(loggedInUserId);
		return stockHoldingList;
	}
	
	@GetMapping(value = "/chart")
	public @ResponseBody List<StockHoldingDTO> getChart(@RequestParam String loggedInUserId) {
		List<StockHoldingDTO> chartData = stockService.getStockHoldoings(loggedInUserId);
		return chartData;
	}
	


	
	

}
