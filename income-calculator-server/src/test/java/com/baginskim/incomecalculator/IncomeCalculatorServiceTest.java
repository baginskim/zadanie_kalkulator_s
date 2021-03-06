package com.baginskim.incomecalculator;

import static com.baginskim.incomecalculator.IncomeCalculatorTest.DAY_PER_MONTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IncomeCalculatorServiceTest {

	@Before
	public void setUp() {
		incomeCalculatorService =
				new IncomeCalculatorService(new IncomeCalculator(DAY_PER_MONTH),
						rateGetterMock,
						incomeCountryRepositoryMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForInvalidCountry() {
		//given

		//when
		incomeCalculatorService.getIncome(COUNTRY_ID, SALARY);

		//then
		// exception is thrown
	}

	@Test
	public void shouldCalculateIncome() {
		//given
		when(rateGetterMock.getRate("USD")).thenReturn(Optional.of(BigDecimal.TEN));
		when(incomeCountryRepositoryMock.findById(COUNTRY_ID)).thenReturn(Optional.of(IncomeCountry.builder()
				.tax((byte) 5)
				.currency("USD")
				.cost(500)
				.build()));

		//when
		BigDecimal income = incomeCalculatorService.getIncome(COUNTRY_ID, SALARY);

		//then
		assertThat(income).isEqualTo(new BigDecimal("167450.00"));
	}


	@Test(expected = IllegalStateException.class)
	public void shouldHandleNoResonseFromRateGetter() {
		//given
		when(rateGetterMock.getRate("USD")).thenReturn(Optional.empty());
		when(incomeCountryRepositoryMock.findById(COUNTRY_ID)).thenReturn(Optional.of(IncomeCountry.builder()
				.tax((byte) 5)
				.currency("USD")
				.cost(500)
				.build()));

		//when
		incomeCalculatorService.getIncome(COUNTRY_ID, SALARY);

		//then
		// exception is thrown
	}

	@Test
	public void shouldHandlePlnCurrency() {
		//given
		when(incomeCountryRepositoryMock.findById(COUNTRY_ID)).thenReturn(Optional.of(IncomeCountry.builder()
				.tax((byte) 5)
				.currency("PLN")
				.cost(500)
				.build()));

		//when
		BigDecimal income = incomeCalculatorService.getIncome(COUNTRY_ID, SALARY);

		//then
		assertThat(income).isEqualTo(new BigDecimal("16745.00"));
	}

	private static final Long COUNTRY_ID = 1L;

	private static final int SALARY = 800;

	private IncomeCalculatorService incomeCalculatorService;

	@Mock
	private RateGetter rateGetterMock;

	@Mock
	private IncomeCountryRepository incomeCountryRepositoryMock;
}