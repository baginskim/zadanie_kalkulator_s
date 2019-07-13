package com.baginskim.incomecalculator;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RateGetter {

	public BigDecimal getRate(String currency) {
		ResponseEntity<ExchangeRatesSeries>
				exchangeRate =
				restTemplate.getForEntity(exchangeRateUrl + currency, ExchangeRatesSeries.class);
		if (exchangeRate.getBody().getRates() == null || exchangeRate.getBody().getRates().isEmpty()) {
			throw new IllegalStateException();
		}
		return exchangeRate.getBody().getRates().get(0).getMid();
	}

	@Data
	static class ExchangeRate {

		private BigDecimal mid;
	}

	@Data
	static class ExchangeRatesSeries {

		private List<ExchangeRate> rates;
	}

	private final String exchangeRateUrl;

	private final RestTemplate restTemplate;

}
