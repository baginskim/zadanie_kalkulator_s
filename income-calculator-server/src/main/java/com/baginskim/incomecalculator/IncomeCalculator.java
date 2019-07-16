package com.baginskim.incomecalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class IncomeCalculator {

	BigDecimal calculateIncomeInPln(int grossDailySalary, IncomeCountry incomeCountry, BigDecimal rate) {
		return incomeInForeignCurrency(incomeCountry, grossDailySalary).multiply(rate)
				.setScale(TWO_DIGITS_SCALE, RoundingMode.DOWN);
	}

	private static final byte TWO_DIGITS_SCALE = 2;

	private static final BigDecimal HUNDRED_PERCENT = new BigDecimal(100);

	private BigDecimal incomeInForeignCurrency(IncomeCountry incomeCountry, int grossDailySalary) {
		int monthlySalary = grossDailySalary * dayPerMonth;

		BigDecimal toTax = salarayToTax(incomeCountry, monthlySalary);

		return new BigDecimal(monthlySalary).subtract(toTax.multiply(new BigDecimal(incomeCountry.getTax()).divide(
				HUNDRED_PERCENT)));
	}

	private BigDecimal salarayToTax(IncomeCountry incomeCountry, long monthlySalary) {
		return monthlySalary < incomeCountry.getCost() ?
				BigDecimal.ZERO :
				BigDecimal.valueOf(monthlySalary - incomeCountry.getCost());
	}

	private final byte dayPerMonth;

}
