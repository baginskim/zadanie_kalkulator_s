package com.baginskim.incomecalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RateGetter {

	@CircuitBreaker(name = "nbppl", fallbackMethod = "fallback")
	public Optional<BigDecimal> getRate(String currency) {
		ResponseEntity<ExchangeRatesSeries>
				exchangeRate =
				restTemplate.getForEntity(exchangeRateUrl + currency, ExchangeRatesSeries.class);
		if (exchangeRate.getBody().getRates() == null || exchangeRate.getBody().getRates().isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(exchangeRate.getBody().getRates().get(0).getMid());
	}

	public Optional<BigDecimal> fallback(String currency, Exception e){
		return Optional.empty();
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
