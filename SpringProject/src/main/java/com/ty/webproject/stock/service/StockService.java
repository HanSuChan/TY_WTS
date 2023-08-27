package com.ty.webproject.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.webproject.balance.dto.BalanceDTO;
import com.ty.webproject.stock.controller.StockHistoryAndHoldingController;
import com.ty.webproject.stock.dto.FinancialStatementsDTO;
import com.ty.webproject.stock.dto.FinancialStatementsItemDTO;
import com.ty.webproject.stock.dto.NewsDTO;
import com.ty.webproject.stock.dto.StockChartDTO;
import com.ty.webproject.stock.dto.StockCurrentPriceDTO;
import com.ty.webproject.stock.dto.StockCurrentPriceInfoDTO;
import com.ty.webproject.stock.dto.StockDTO;
import com.ty.webproject.stock.dto.StockExchangeDTO;
import com.ty.webproject.stock.dto.StockHistoryDTO;
import com.ty.webproject.stock.dto.StockHoldingDTO;
import com.ty.webproject.stock.dto.StockIndexDTO;
import com.ty.webproject.stock.dto.StockListDTO;
import com.ty.webproject.stock.dto.StockRiseDTO;
import com.ty.webproject.stock.repository.StockRepository;
import com.ty.webproject.stock.dto.NewsDTO.NewsItem;

@Service
public class StockService {
	
	private static final Logger logger = LoggerFactory.getLogger(StockService.class);

	private static final String EXTERNAL_API_URL = "http://localhost:5000/";
	@Autowired
	private StockRepository stockRepository;

	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	public void insertStockCode(List<StockDTO> stockDTO) {
		stockRepository.insertStockCode(stockDTO);
	}

	public StockListDTO getStockList() {
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate.getForObject(EXTERNAL_API_URL + "getStockList", String.class);
		System.out.println(responseBody);

		// JSON 문자열을 StockListDTO 객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		StockListDTO stockListDTO = null;
		try {
			stockListDTO = objectMapper.readValue(responseBody, StockListDTO.class);
		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
		}

		return stockListDTO;
	}

	public StockCurrentPriceInfoDTO getStockCurrentPriceInfo(String stockcode) {
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate
				.getForObject(EXTERNAL_API_URL + "getStockCurrentPriceInfo?stockcode=" + stockcode, String.class);
		System.out.println(responseBody);

		ObjectMapper objectMapper = new ObjectMapper();
		StockCurrentPriceInfoDTO sotckcCurrentPriceInfoDTO = null;
		try {
			sotckcCurrentPriceInfoDTO = objectMapper.readValue(responseBody, StockCurrentPriceInfoDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sotckcCurrentPriceInfoDTO;

	}

	public NewsDTO getStockNews() {
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate.getForObject(EXTERNAL_API_URL + "StockNews", String.class);
		System.out.println(responseBody);

		ObjectMapper objectMapper = new ObjectMapper();
		NewsDTO newsDTO = null;
		try {
			newsDTO = objectMapper.readValue(responseBody, NewsDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<NewsDTO.NewsItem> result = new ArrayList<>();
		for (NewsDTO.NewsItem item : newsDTO.getNewsData()) {
			boolean isDuplicate = false;
			for (NewsDTO.NewsItem innerItem : result) {
				if (item.getContent().equals(innerItem.getContent())) {
					isDuplicate = true;
					break;
				}
			}
			if (!isDuplicate) {
				result.add(item);
			}
		}

		newsDTO.setNewsData(result);
		return newsDTO;
	}

	public FinancialStatementsDTO getFinancialStatements(String stockcode) {
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate
				.getForObject(EXTERNAL_API_URL + "getFinancialStatements?stockcode=" + stockcode, String.class);
		responseBody = responseBody.replaceAll("'", "\"");
		System.out.println(responseBody);

		ObjectMapper objectMapper = new ObjectMapper();

		FinancialStatementsDTO financialstatements = null;

		try {
			financialstatements = objectMapper.readValue(responseBody, FinancialStatementsDTO.class);

		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
		}

		return financialstatements;
	}

	public StockChartDTO getStockChart(String stockcode) {
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate.getForObject(EXTERNAL_API_URL + "getStockChart?stockcode=" + stockcode,
				String.class);
		System.out.println(responseBody);

		ObjectMapper objectMapper = new ObjectMapper();

		StockChartDTO stockChart = null;

		try {
			stockChart = objectMapper.readValue(responseBody, StockChartDTO.class);
			System.out.println(stockChart);

		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
		}
		return stockChart;
	}
	public StockIndexDTO stockIndex() {
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate.getForObject(EXTERNAL_API_URL + "StockIndex",
				String.class);
		System.out.println(responseBody);
		ObjectMapper objectMapper = new ObjectMapper();

		StockIndexDTO stockIndexDTO  = null;

		try {
			stockIndexDTO = objectMapper.readValue(responseBody, StockIndexDTO.class);
		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
		}
		return stockIndexDTO;
	}

	public String getStockCode(String stockItem) {

		String code = stockRepository.getStockCode(stockItem);

		return code;

	}

	public List<Map<String, Object>> autocomplete(Map<String, Object> paramMap) throws Exception {
		return stockRepository.autocomplete(paramMap);
	}

	public StockRiseDTO getStockRise() {
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate.getForObject(EXTERNAL_API_URL + "StockRise", String.class);
		System.out.println(responseBody);

		ObjectMapper objectMapper = new ObjectMapper();
		StockRiseDTO stockRise = null;
		try {
			stockRise = objectMapper.readValue(responseBody, StockRiseDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockRise;
	}

	public StockExchangeDTO getStockExchange() {
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate.getForObject(EXTERNAL_API_URL + "StockExchange", String.class);
		System.out.println(responseBody);

		ObjectMapper objectMapper = new ObjectMapper();
		StockExchangeDTO stockExchangeDTO = null;
		try {
			stockExchangeDTO = objectMapper.readValue(responseBody, StockExchangeDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockExchangeDTO;
	}

	public List<StockHistoryDTO> getStockHistory(String member_Id) {
		logger.info("stockservice id= " + member_Id);

		return stockRepository.getStockHistory(member_Id);

	}
	public List<StockHoldingDTO> getStockHoldoings(String member_Id) {
		logger.info("getStockHoldoings id= " + member_Id);

		return stockRepository.getStockHoldoings(member_Id);

	}
}
