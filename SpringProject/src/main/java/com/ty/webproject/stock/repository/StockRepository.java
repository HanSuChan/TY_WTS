package com.ty.webproject.stock.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ty.webproject.stock.dto.StockDTO;
import com.ty.webproject.stock.dto.StockHistoryDTO;
import com.ty.webproject.stock.dto.StockHoldingDTO;
import com.ty.webproject.stock.dto.StockListDTO;

@Mapper
public interface StockRepository {
	public void insertStockCode(List<StockDTO> stockDTO);

	public String getStockCode(String stockItem);

	List<Map<String, Object>> autocomplete(Map<String, Object> paramMap) throws Exception;
	
	public List<StockHistoryDTO> getStockHistory(String member_Id);
	
	public List<StockHoldingDTO> getStockHoldoings(String member_Id);	

	public StockHoldingDTO getStockHoldoingById(@Param("stockCode") String stockCode, @Param("member_Id") String member_Id);
	
	public void insertStockHoldingById(StockHoldingDTO stockHoldingDTO);
	
	public void updateStockHoldingById(StockHoldingDTO stockHoldingDTO);
	
	public void deleteStockHoldingById(StockHoldingDTO stockHoldingDTO);
	 
    public void insertStockHistory(Map<String, Object> paramMap);

}
