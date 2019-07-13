package com.baginskim.incomecalculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class IncomeCalculatorTest {

	@Test
	public void shouldGetIncomeWithNoCost() {
		//given
		IncomeCountry incomeCountry = IncomeCountry.builder().cost(0).tax((byte) 10).build();
		int grossDailySalary = 100;

		//when
		BigDecimal bigDecimal = calculator.calculateIncomeInPln(grossDailySalary, incomeCountry, CHF_RATE);

		//then
		assertThat(bigDecimal).isEqualTo(new BigDecimal(1980.00).multiply(CHF_RATE));
	}

	@Test
	public void shouldGetIncome() {
		//given
		IncomeCountry incomeCountry = IncomeCountry.builder().cost(1100).tax((byte) 10).build();
		int grossDailySalary = 100;

		//when
		BigDecimal bigDecimal = calculator.calculateIncomeInPln(grossDailySalary, incomeCountry, CHF_RATE);

		//then
		assertThat(bigDecimal).isEqualTo(new BigDecimal(2090.00).multiply(CHF_RATE));
	}

	@Test
	public void shouldHandleZeroSallaryPerDay() {
		//given
		IncomeCountry incomeCountry = IncomeCountry.builder().cost(0).tax((byte) 10).build();

		//when
		BigDecimal bigDecimal = calculator.calculateIncomeInPln(0, incomeCountry, CHF_RATE);

		//then
		assertThat(bigDecimal).isZero();
	}

	@Test
	public void shouldGetNoTaxWhenCostAreGreaterThanMonhtlySalary() {
		//given
		IncomeCountry incomeCountry = IncomeCountry.builder().cost(300).tax((byte) 10).build();

		//when
		BigDecimal bigDecimal = calculator.calculateIncomeInPln(10, incomeCountry, CHF_RATE);

		//then
		assertThat(bigDecimal).isEqualTo(new BigDecimal(220).multiply(CHF_RATE));
	}

	@Test
	public void shouldHandle100PercentTaxWithNoCost() {
		//given
		IncomeCountry incomeCountry = IncomeCountry.builder().cost(0).tax((byte) 100).build();
		int grossDailySalary = 100;

		//when
		BigDecimal bigDecimal = calculator.calculateIncomeInPln(grossDailySalary, incomeCountry, CHF_RATE);

		//then
		assertThat(bigDecimal).isZero();
	}

	static final byte DAY_PER_MONTH = 22;

	private static final BigDecimal CHF_RATE = BigDecimal.valueOf(3.4500);

	private IncomeCalculator calculator = new IncomeCalculator(DAY_PER_MONTH);

}