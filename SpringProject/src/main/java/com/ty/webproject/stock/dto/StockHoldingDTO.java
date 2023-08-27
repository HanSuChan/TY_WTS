package com.ty.webproject.stock.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockHoldingDTO {
	
	private String stockCode;
	private String stockName;
	private int stockPrice;
	private int stockQuantity;
	private String member_id;
	
    @Override
    public String toString() {
        return "StockHoldingDTO{" +
               "stockName='" + stockName + '\'' +
               ", stockPrice=" + stockPrice +
               ", stockQuantity=" + stockQuantity +
               '}';
    }

}
