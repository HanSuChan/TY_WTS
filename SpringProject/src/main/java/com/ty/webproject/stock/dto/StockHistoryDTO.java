package com.ty.webproject.stock.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockHistoryDTO {	
    // stockid 컬럼은 시퀀스로 자동 생성되므로 DTO 필드에 포함하지 않습니다.
    private String userstock_code;
    private String userstock_name;
    private int price;
    private int quantity;
    private String member_id;
    private String type;
    private Date trading_date;
}
