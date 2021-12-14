package com.softland.spring.mockito.api.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.softland.spring.mockito.util.StockApiClient;

@Service
public class StockService {
	private final StockApiClient stockApiClient;

	private Set<String> techCompanies = new HashSet<String>(Arrays.asList("AAPL", "MSFT", "GOOG"));

	public StockService(StockApiClient stockApiClient) {
		this.stockApiClient = stockApiClient;
	}

	public BigDecimal getLatestPrice(String stockCode) {
		if (techCompanies.contains(stockCode)) {
			return BigDecimal.valueOf(Double.MAX_VALUE);
		}

		try {
			return stockApiClient.getLatestStockPrice(stockCode);
		} catch (Exception e) {
			e.printStackTrace();
			return BigDecimal.ZERO;
		}
	}
}
