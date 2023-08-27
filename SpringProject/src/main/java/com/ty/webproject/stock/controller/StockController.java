package com.ty.webproject.stock.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.webproject.stock.dto.FinancialStatementsDTO;
import com.ty.webproject.stock.dto.NewsDTO;
import com.ty.webproject.stock.dto.StockChartDTO;
import com.ty.webproject.stock.dto.StockCurrentPriceDTO;
import com.ty.webproject.stock.dto.StockCurrentPriceInfoDTO;
import com.ty.webproject.stock.dto.StockHistoryDTO;
import com.ty.webproject.stock.dto.StockListDTO;
import com.ty.webproject.stock.service.StockService;

@Controller
public class StockController {
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);

	private final StockService stockService;

	// StockService를 주입받는 생성자
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

//	@RequestMapping(value = "/getStockList", method = RequestMethod.GET)
//	public ModelAndView getStockList() throws JsonMappingException, JsonProcessingException {
//		ModelAndView mav = new ModelAndView();
//		StockListDTO stockListDTO = stockService.getStockList();
//
//		mav.addObject("test", stockListDTO);
//		mav.addObject("fail", false);
//		mav.setViewName("/stock/test"); // jsp파일 이름
//		return mav;
//	}

	@RequestMapping(value = "/getStockCurrentPriceInfo", method = RequestMethod.GET)
	public ModelAndView getStockCurrentPriceInfo() throws JsonMappingException, JsonProcessingException {
		String stockcode = "005930";
		ModelAndView mav = new ModelAndView();
		StockCurrentPriceInfoDTO sotckcCurrentPriceInfoDTO = stockService.getStockCurrentPriceInfo(stockcode);
		mav.addObject("stock", sotckcCurrentPriceInfoDTO);
		mav.addObject("fail", false);
		mav.setViewName("/stock/StockCurrentPriceInfo"); // jsp파일 이름
		return mav;
	}

	@RequestMapping(value = "/StockNews", method = RequestMethod.GET)
	public ModelAndView getStockNews() throws JsonMappingException, JsonProcessingException {
		ModelAndView mav = new ModelAndView();
		NewsDTO newsDTO = stockService.getStockNews();
		mav.addObject("stockNews", newsDTO);
		mav.addObject("fail", false);
		mav.setViewName("/stock/StockNews"); // jsp파일 이름
		return mav;
	}

	@RequestMapping(value = "/getStockChart", method = RequestMethod.GET)
	public String getStockChart(Model model) throws JsonMappingException, JsonProcessingException {
		String stockcode = "A005930";
		StockChartDTO stockChart = stockService.getStockChart(stockcode);
		model.addAttribute("stockChart", stockChart);
		return "/stock/CandleChart";

	}

	@RequestMapping(value = "/GetStockInfo")
	public String blank() {
		return "/stock/GetStockInfo";
	}

	@RequestMapping(value = "/ajax/autocomplete.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> autoComplete(@RequestParam Map<String, Object> paramMap) throws Exception {
		List<Map<String, Object>> resultList = stockService.autocomplete(paramMap);
		paramMap.put("resultList", resultList);

		logger.info("paramMapAutoComplete= " + resultList);

		return paramMap;
	}

	@RequestMapping(value = "/StockCodeAndName", method = RequestMethod.GET)
	public ModelAndView getStockCodeAndName(@RequestParam Map<String, String> paramMap, Model model) {
		String stockCode = paramMap.get("code");
		String stockName = paramMap.get("name");

		ModelAndView mav = new ModelAndView();
		FinancialStatementsDTO financialstatements = stockService.getFinancialStatements(stockCode);
		StockChartDTO stockChartDTO = stockService.getStockChart(stockCode);
		mav.addObject("financialstatements", financialstatements);
		mav.addObject("stockCode", stockCode);
		mav.addObject("stockName", stockName);
		mav.addObject("stockChart", stockChartDTO);
		mav.addObject("fail", false);
		mav.setViewName("/stock/StockInfo"); // jsp파일 이름

		logger.info("getStockCode= " + stockCode);
		logger.info("getStockName= " + stockName);

		return mav; // 적절한 뷰 페이지 이름을 반환
	}

	@GetMapping("/stockHistory")
	public ResponseEntity<List<StockHistoryDTO>> getStockHistory(@RequestParam("memberId") String memberId) {
		List<StockHistoryDTO> stockHistoryList = stockService.getStockHistory(memberId);

		if (stockHistoryList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(stockHistoryList);
	}

}
