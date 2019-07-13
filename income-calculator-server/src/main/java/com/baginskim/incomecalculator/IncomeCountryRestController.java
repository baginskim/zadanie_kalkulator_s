package com.baginskim.incomecalculator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
class IncomeCountryRestController {

	@GetMapping(path = "countries")
	public List<IncomeCountry> getCountries() {
		log.info("> getCountries");

		ArrayList result = new ArrayList();
		incomeCountryRepository.findAll().forEach(result::add);

		log.info("< getIncome result={}", result);
		return result;
	}

	@Autowired
	private IncomeCountryRepository incomeCountryRepository;

}
