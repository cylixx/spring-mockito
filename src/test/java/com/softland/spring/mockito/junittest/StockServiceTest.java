/*
 https://rieckpil.de/difference-between-mock-and-mockbean-spring-boot-applications/

*/
package com.softland.spring.mockito.junittest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softland.spring.mockito.api.service.impl.StockService;
import com.softland.spring.mockito.util.StockApiClient;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {
	
	@Mock
	private StockApiClient stockApiClient;

	@InjectMocks
	private StockService cut;

	@Test
	void shouldReturnDefaultPriceWhenClientThrowsException() {

		when(stockApiClient.getLatestStockPrice("AMZN")).thenThrow(new RuntimeException("Remote System Down!"));

		BigDecimal result = cut.getLatestPrice("AMZN");

		assertEquals(BigDecimal.ZERO, result);
	}

}
