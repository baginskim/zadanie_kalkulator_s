package com.baginskim.incomecalculator;

import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class IncomeCalculatorService {

	public BigDecimal getIncome(Long countryId, int dailySalary) {
		Optional<IncomeCountry> incomeCountryOptional = incomeCountryRepository.findById(countryId);
		incomeCountryOptional.orElseThrow(IllegalArgumentException::new);

		return incomeCalculator.calculateIncomeInPln(
				dailySalary,
				incomeCountryOptional.get(),
				isPln(incomeCountryOptional) ? ONE : rateGetter.getRate(incomeCountryOptional.get().getCurrency()));
	}

	private boolean isPln(Optional<IncomeCountry> incomeCountryOptional) {
		return "PLN".equalsIgnoreCase(incomeCountryOptional.get().getCurrency());
	}

	private final IncomeCalculator incomeCalculator;

	private final RateGetter rateGetter;

	private final IncomeCountryRepository incomeCountryRepository;
}
