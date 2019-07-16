package com.baginskim.incomecalculator;

import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class IncomeCalculatorService {

	public BigDecimal getIncome(Long countryId, int dailySalary) {
		Optional<IncomeCountry> incomeCountryOptional = incomeCountryRepository.findById(countryId);
		IncomeCountry incomeCountry = incomeCountryOptional.orElseThrow(IllegalArgumentException::new);

		return incomeCalculator.calculateIncomeInPln(
				dailySalary,
				incomeCountry,
				isPln(incomeCountry) ?
						ONE :
						rateGetter.getRate(incomeCountry.getCurrency()).orElseThrow(IllegalStateException::new));
	}

	private boolean isPln(IncomeCountry incomeCountryOptional) {
		return "PLN".equalsIgnoreCase(incomeCountryOptional.getCurrency());
	}

	private final IncomeCalculator incomeCalculator;

	private final RateGetter rateGetter;

	private final IncomeCountryRepository incomeCountryRepository;
}
