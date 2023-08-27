package com.ty.webproject.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ty.webproject.stock.dto.NewsDTO;
import com.ty.webproject.stock.dto.StockExchangeDTO;
import com.ty.webproject.stock.dto.StockIndexDTO;
import com.ty.webproject.stock.dto.StockRiseDTO;
import com.ty.webproject.stock.service.StockService;

@Controller
public class HomeController {
	private final StockService stockService;

	// StockService를 주입받는 생성자
	public HomeController(StockService stockService) {
		this.stockService = stockService;
	}

	@RequestMapping(value = "/home")
	public ModelAndView Home() {
		ModelAndView mav = new ModelAndView();

		NewsDTO newsDTO = stockService.getStockNews();
		StockRiseDTO stockRise = stockService.getStockRise();
		StockExchangeDTO stockExchangeDTO = stockService.getStockExchange();
		StockIndexDTO stockIndexDTO = stockService.stockIndex();

		mav.addObject("stockNews", newsDTO);
		mav.addObject("stockRise", stockRise);
		mav.addObject("stockExchange", stockExchangeDTO);
		mav.addObject("kospi", stockIndexDTO.getData().get("KOSPI"));
		mav.addObject("kosdaq", stockIndexDTO.getData().get("KOSDAQ"));

		mav.addObject("fail", false);
		mav.setViewName("/home/index"); // jsp파일 이름
		return mav;
	}

	@RequestMapping(value = "/blank")
	public String blank() {
		return "/home/blank";
	}
}
