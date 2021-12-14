package com.softland.spring.mockito.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softland.spring.mockito.api.service.impl.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

//  public StockController(StockService stockService) {
//    this.stockService = stockService;
//  }

	@GetMapping
	public BigDecimal getStockPrice(@RequestParam("stockCode") String stockCode) {
		return stockService.getLatestPrice(stockCode);
	}
}
