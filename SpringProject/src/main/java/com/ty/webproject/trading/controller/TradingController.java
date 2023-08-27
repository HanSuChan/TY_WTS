package com.ty.webproject.trading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.reader.UnicodeReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ty.webproject.balance.dto.BalanceDTO;
import com.ty.webproject.balance.service.BalanceService;
import com.ty.webproject.stock.dto.StockChartDTO;
import com.ty.webproject.stock.dto.StockCurrentPriceInfoDTO;
import com.ty.webproject.stock.dto.StockHoldingDTO;
import com.ty.webproject.stock.service.StockService;
import com.ty.webproject.trading.dto.OrderBookDTO;
import com.ty.webproject.trading.service.TradingService;

@Controller
public class TradingController {
	private final StockService stockService;
	private final TradingService tradingService;
	private final BalanceService balanceService;

	// StockService를 주입받는 생성자
	@Autowired
	public TradingController(StockService stockService, TradingService tradingService, BalanceService balanceService) {
		this.stockService = stockService;
		this.tradingService = tradingService;
		this.balanceService = balanceService;

	}

	@RequestMapping(value = "/trading", method = RequestMethod.GET)
	public ModelAndView trading(@RequestParam String stockCode, @RequestParam String stockName)
			throws JsonMappingException, JsonProcessingException {
		ModelAndView mav = new ModelAndView();
		StockCurrentPriceInfoDTO sotckcCurrentPriceInfoDTO = stockService.getStockCurrentPriceInfo(stockCode);
		StockChartDTO stockChartDTO = stockService.getStockChart(stockCode);

		mav.addObject("stockCurrentInfo", sotckcCurrentPriceInfoDTO);
		mav.addObject("stockCode", stockCode);
		mav.addObject("stockChart", stockChartDTO);
		mav.addObject("stockName", stockName);
		mav.setViewName("/trading/trading");
		return mav;
	}

	@GetMapping("/OrderBookData")
	public @ResponseBody OrderBookDTO getOrderBookData(@RequestParam String stockCode)
			throws JsonMappingException, JsonProcessingException {
		return tradingService.getOrderBookData(stockCode);
	}

	@GetMapping(value = "/balance")
	public @ResponseBody BalanceDTO getBalanceData(@RequestParam String loggedIn_id)
			throws JsonMappingException, JsonProcessingException {
		return balanceService.getBalanceData(loggedIn_id);
	}

	@GetMapping(value = "/order")
	public @ResponseBody StockHoldingDTO order(@RequestParam String loggedInUserId, @RequestParam String stockCode,
			@RequestParam String stockName, @RequestParam int price, @RequestParam int quantity,
			@RequestParam String action) {
		return tradingService.order(loggedInUserId, stockCode, stockName, price, quantity, action);
	}

	@GetMapping(value = "/stockHolding")
	public @ResponseBody StockHoldingDTO stockHolding(@RequestParam String stockCode,
			@RequestParam String loggedInUserId) {
		System.out.println("Test: " + tradingService.stockHolding(stockCode, loggedInUserId));
		return tradingService.stockHolding(stockCode, loggedInUserId);
	}

	@GetMapping(value = "/concludeOrder")
	public @ResponseBody String concludeOrder(@RequestParam String loggedInUserId, @RequestParam String stockCode,
			@RequestParam String stockName, @RequestParam int orderedQuantity, @RequestParam int orderedPrice,
			@RequestParam String orderedAction, @RequestParam int currentPrice) {
		// 여기서 주문 체결 여부를 확인하고 처리하는 로직을 구현
		// 주문 체결이 완료되었다면 "true"를, 아직 미처리 상태라면 "false"를 반환
		return tradingService.concludeOrder(loggedInUserId, stockCode, stockName, orderedQuantity, orderedPrice,
				orderedAction, currentPrice);
	}

}