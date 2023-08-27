package com.ty.webproject.stock.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class NewsDTO {
    private List<NewsItem> newsData;

    public NewsDTO() {
    }

    public List<NewsItem> getNewsData() {
        return newsData;
    }

    public void setNewsData(List<NewsItem> newsData) {
        this.newsData = newsData;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewsItem {
    	private String time;
    	private String code;
        private String name;
        private String content;
    }
}
