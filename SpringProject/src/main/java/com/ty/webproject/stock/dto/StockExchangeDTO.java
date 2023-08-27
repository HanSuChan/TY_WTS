package com.ty.webproject.stock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class StockExchangeDTO {
	private List<StockExchangeItem> exchange;
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class StockExchangeItem {
		private String price;
		private String name;
	}
}
