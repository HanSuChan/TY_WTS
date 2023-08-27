package com.ty.webproject.stock.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CandleDataDTO {
	private List<CandleDataItem> chartData;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CandleDataItem {
		private int date;
		private int vol;
		private int open;
		private int high;
		private int low;
		private int close;
	}
}
