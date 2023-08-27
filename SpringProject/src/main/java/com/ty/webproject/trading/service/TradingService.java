package com.ty.webproject.trading.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.webproject.balance.dto.BalanceDTO;
import com.ty.webproject.balance.repository.BalanceRepository;
import com.ty.webproject.stock.dto.StockHoldingDTO;
import com.ty.webproject.stock.dto.StockListDTO;
import com.ty.webproject.stock.repository.StockRepository;
import com.ty.webproject.trading.dto.OrderBookDTO;
import com.ty.webproject.trading.repository.TradingRepository;

@Service
public class TradingService {
	private TradingRepository tradingRepository;
	private BalanceRepository balanceRepository;
	private StockRepository stockRepository;
	private static final String EXTERNAL_API_URL = "http://localhost:5000/";

	@Autowired
	public TradingService(TradingRepository tradingRepository, BalanceRepository balanceRepository,
			StockRepository stockRepository) {
		this.stockRepository = stockRepository;
		this.tradingRepository = tradingRepository;
		this.balanceRepository = balanceRepository;

	}

	public OrderBookDTO getOrderBookData(String stockcode) {
		System.out.println("T : " + stockcode);
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = restTemplate.getForObject(EXTERNAL_API_URL + "OrderBook?stockcode=" + stockcode,
				String.class);
		System.out.println(responseBody);

		// JSON 문자열을 StockListDTO 객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		OrderBookDTO orderBookDTO = null;
		try {
			orderBookDTO = objectMapper.readValue(responseBody, OrderBookDTO.class);
		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
		}

		return orderBookDTO;
	}

	public StockHoldingDTO order(String loggedInUserId, String stockCode, String stockName, int price, int quantity,
			String action) {
		int balance = balanceRepository.selectBalanceData(loggedInUserId).getBalance();

		if (balance >= quantity * price && action.equals("buy")) {
			return new StockHoldingDTO(stockCode, stockName, price, quantity, loggedInUserId);
		} else if (action.equals("sell")) {
			// stockcode로 조회하고 있으면 return DTO 없으면 null
			StockHoldingDTO stockHoldingDTO = stockRepository.getStockHoldoingById(stockCode, loggedInUserId);
			if (stockHoldingDTO != null && stockHoldingDTO.getStockQuantity() - quantity >= 0) {
				return new StockHoldingDTO(stockCode, stockName, price, quantity, loggedInUserId);
			}
		}
		return null;
	}

	public StockHoldingDTO stockHolding(String stockCode, String member_id) {
		return stockRepository.getStockHoldoingById(stockCode, member_id);
	}

	public String concludeOrder(String loggedInUserId, String stockCode, String stockName, int orderedQuantity,
			int orderedPrice, String orderedAction, int currentPrice) {
		System.out.println("Test:" + loggedInUserId + stockCode + stockName + orderedQuantity + orderedPrice
				+ orderedAction + currentPrice);
		int balance = balanceRepository.selectBalanceData(loggedInUserId).getBalance();
		if (orderedPrice == currentPrice && orderedAction.equals("매수")) {
			if (orderedPrice * orderedQuantity <= balance) {
				StockHoldingDTO stockHoldingDTO = stockRepository.getStockHoldoingById(stockCode, loggedInUserId);
				if (stockHoldingDTO == null) {
					// 조회 결과 Null -> 조회 된 DTO없음
					// DTO를 insert해야함

					stockRepository.insertStockHoldingById(
							new StockHoldingDTO(stockCode, stockName, orderedPrice, orderedQuantity, loggedInUserId));

					LocalDateTime now = LocalDateTime.now();

					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("stockHoldingDTO",
							new StockHoldingDTO(stockCode, stockName, orderedPrice, orderedQuantity, loggedInUserId));
					paramMap.put("type", "buy");
					paramMap.put("date", now);

					stockRepository.insertStockHistory(paramMap);
					// balance 빼야함.
					balance = balance - (orderedPrice * orderedQuantity);
					balanceRepository.updateBalanceData(new BalanceDTO(balance, loggedInUserId));
					return "true";

				} else {
					// 조회 결과 Exist -> 조회 된 DTO 있음
					LocalDateTime now = LocalDateTime.now();

					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("stockHoldingDTO",
							new StockHoldingDTO(stockCode, stockName, orderedPrice, orderedQuantity, loggedInUserId));
					paramMap.put("type", "buy");
					paramMap.put("date", now);

					stockRepository.insertStockHistory(paramMap);
					int totalStockValue = orderedPrice * orderedQuantity
							+ stockHoldingDTO.getStockPrice() * stockHoldingDTO.getStockQuantity();
					int totalQuantity = orderedQuantity + stockHoldingDTO.getStockQuantity();
					int newStockPrice = totalStockValue / totalQuantity;

					stockHoldingDTO.setStockPrice(newStockPrice);
					stockHoldingDTO.setStockQuantity(totalQuantity);
					// stockHolding update && balance update
					stockRepository.updateStockHoldingById(stockHoldingDTO);
					balance = balance - (orderedPrice * orderedQuantity);
					balanceRepository.updateBalanceData(new BalanceDTO(balance, loggedInUserId));
					return "true";
				}

			}
		} else if (orderedPrice == currentPrice && orderedAction.equals("매도")) {
			StockHoldingDTO stockHoldingDTO = stockRepository.getStockHoldoingById(stockCode, loggedInUserId);
			if (stockHoldingDTO != null) {
				// DTO.Q ->팔 수 있는 물량
				// order.Q -> 주문 한 물량
				if (stockHoldingDTO.getStockQuantity() >= orderedQuantity) {
					if (stockHoldingDTO.getStockQuantity() - orderedQuantity == 0) {
						// delete
						LocalDateTime now = LocalDateTime.now();

						Map<String, Object> paramMap = new HashMap<>();
						paramMap.put("stockHoldingDTO", new StockHoldingDTO(stockCode, stockName, orderedPrice,
								orderedQuantity, loggedInUserId));
						paramMap.put("type", "sell");
						paramMap.put("date", now);

						stockRepository.insertStockHistory(paramMap);
						stockRepository.deleteStockHoldingById(stockHoldingDTO);
						balance = balance + (orderedPrice * orderedQuantity);
						balanceRepository.updateBalanceData(new BalanceDTO(balance, loggedInUserId));
						return "true";
					} else {
						// DTO update
						LocalDateTime now = LocalDateTime.now();

						Map<String, Object> paramMap = new HashMap<>();
						paramMap.put("stockHoldingDTO", new StockHoldingDTO(stockCode, stockName, orderedPrice,
								orderedQuantity, loggedInUserId));
						paramMap.put("type", "sell");
						paramMap.put("date", now);

						stockRepository.insertStockHistory(paramMap);
						stockHoldingDTO.setStockQuantity(stockHoldingDTO.getStockQuantity() - orderedQuantity);
						stockRepository.updateStockHoldingById(stockHoldingDTO);
						balance = balance + (orderedPrice * orderedQuantity);
						balanceRepository.updateBalanceData(new BalanceDTO(balance, loggedInUserId));
						return "true";
					}

				}

			}

		}
		return "false";
	}

}
