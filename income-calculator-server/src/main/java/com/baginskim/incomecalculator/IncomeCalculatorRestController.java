package com.baginskim.incomecalculator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
class IncomeCalculatorRestController {

	@GetMapping(path = "income/{countryId}/{dailySalary}")
	public BigDecimal getIncome(@PathVariable @NonNull Long countryId, @PathVariable @NonNull int dailySalary) {
		log.info("> getIncome countryId={}. dailySalary={}", countryId, dailySalary);

		BigDecimal result = incomeCalculatorService.getIncome(countryId, dailySalary);

		log.info("< getIncome result={}", result);
		return result;
	}

	@Autowired
	private IncomeCalculatorService incomeCalculatorService;

}
