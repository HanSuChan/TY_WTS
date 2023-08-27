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

public class StockRiseDTO {
	private List<StockItem> riseData;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class StockItem {
		private String code;
		private String name;
		private double diff;
	}
}
