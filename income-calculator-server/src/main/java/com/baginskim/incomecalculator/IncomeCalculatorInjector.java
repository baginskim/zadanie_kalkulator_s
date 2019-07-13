package com.baginskim.incomecalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class IncomeCalculatorInjector {

	@Bean
	public IncomeCalculatorService incomeCalculatorService(RestTemplate restTemplate) {
		return new IncomeCalculatorService(
				new IncomeCalculator(dayPerMonth),
				new RateGetter(exchangeRateUrl, restTemplate),
				incomeCountryRepository);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Value("${dayPerMonth:22}")
	private byte dayPerMonth;

	@Value("${exchangerete.url}")
	private String exchangeRateUrl;

	@Autowired
	private IncomeCountryRepository incomeCountryRepository;

}
