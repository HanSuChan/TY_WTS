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
@JsonIgnoreProperties(ignoreUnknown = true) 
public class FinancialStatementsItemDTO {
	private String rcept_no;
	private String reprt_code;
	private String bsns_year;
	private String corp_code;
	private String sj_div;
	private String sj_nm;
	private String account_id;
	private String account_nm;
	private String account_detail;
	private String thstrm_nm;
	private String thstrm_amount;
	private String frmtrm_nm;
	private String frmtrm_amount;
	private String bfefrmtrm_nm;
	private String bfefrmtrm_amount;
	private String ord;
	private String currency;
}
