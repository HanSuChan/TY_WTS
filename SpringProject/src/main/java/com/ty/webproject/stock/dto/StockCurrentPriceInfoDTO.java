package com.ty.webproject.stock.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // "error" 키를 무시하도록 설정
public class StockCurrentPriceInfoDTO {
	// 현재가 정보 조회
	private String code;       // 종목코드
	private String name;       // 종목명
	private String time;       // 시간
	private int cprice;        // 종가
	private int diff;          // 대비
	private int open;          // 시가
	private int high;          // 고가
	private int low;           // 저가
	private int offer;         // 매도호가
	private int bid;           // 매수호가
	private int vol;           // 거래량
	private int vol_value;     // 거래대금
	

}
