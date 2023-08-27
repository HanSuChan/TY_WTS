package com.ty.webproject.stock.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class StockIndexDTO {
    private Map<String, List<StockIndex>> data;
    @Data
    public static class StockIndex {
        private int date;
        private int close;
        private int high;
        private int low;
        private int open;
    }
}
