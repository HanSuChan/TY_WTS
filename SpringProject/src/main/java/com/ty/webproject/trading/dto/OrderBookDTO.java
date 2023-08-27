package com.ty.webproject.trading.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookDTO {
	
	private int currentPrice;
	private int change;
	private double changePercentage;
	private List<String> buyPrices;
	private List<String> sellPrices;
	
	private int totalSell;
	private int totalBuy;
}
