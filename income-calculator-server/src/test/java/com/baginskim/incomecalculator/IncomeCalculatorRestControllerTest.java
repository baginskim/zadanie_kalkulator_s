package com.baginskim.incomecalculator;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncomeCalculatorRestControllerTest {

	@Test
	public void shouldReturnIncome() {
		// given
		IncomeCountry savedIncomeCountry = incomeCountryRepository.save(createIncomeCountry());

		stubFor(get(urlPathMatching("/api/exchangerates/rates/a/" + savedIncomeCountry.getCurrency())).willReturn(
				aResponse().withStatus(HttpStatus.OK.value())
						.withHeader("Content-Type", "application/json")
						.withBody(
								"{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[{\"no\":\"134/A/NBP/2019\",\"effectiveDate\":\"2019-07-12\",\"mid\":"
										+ RATE
										+ "}]}")));

		//when then
		assertThat(this.restTemplate.getForObject("http://localhost:"
				+ port
				+ "/income/"
				+ savedIncomeCountry.getId()
				+ "/850", String.class)).contains("88849.31");
	}

	@Test
	public void shouldHandleEmptyRate() {
		// given
		IncomeCountry savedIncomeCountry = incomeCountryRepository.save(createIncomeCountry());

		stubFor(get(urlPathMatching("/api/exchangerates/rates/a/" + savedIncomeCountry.getCurrency())).willReturn(
				aResponse().withStatus(HttpStatus.OK.value())
						.withHeader("Content-Type", "application/json")
						.withBody("{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[]}")));

		//when then
		assertThat(this.restTemplate.getForEntity("http://localhost:"
				+ port
				+ "/income/"
				+ savedIncomeCountry.getId()
				+ "/850", String.class).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void shouldHandleRateNotFound() {
		// given
		IncomeCountry savedIncomeCountry = incomeCountryRepository.save(createIncomeCountry());

		stubFor(get(urlPathMatching("/api/exchangerates/rates/a/" + savedIncomeCountry.getCurrency())).willReturn(
				aResponse().withStatus(HttpStatus.NOT_FOUND.value())
						.withHeader("Content-Type", "application/json")
						.withBody(HttpStatus.NOT_FOUND.getReasonPhrase())));

		//when then
		assertThat(this.restTemplate.getForEntity("http://localhost:"
				+ port
				+ "/income/"
				+ savedIncomeCountry.getId()
				+ "/850", String.class).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void shouldHandleInvalidCountry() {
		// given

		//when then
		assertThat(this.restTemplate.getForEntity("http://localhost:"
				+ port
				+ "/income/"
				+ NON_EXISTING_COUNTRY_ID
				+ "/850", String.class).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(1234);

	private static final long INCOME_COUNTRY_ID = -1L;

	private static final String RATE = "4.7513";

	private static final String CURRENCY = "DMK";

	private static final Long NON_EXISTING_COUNTRY_ID = -987L;

	private IncomeCountry createIncomeCountry() {
		return IncomeCountry.builder()
				.id(INCOME_COUNTRY_ID)
				.currency(CURRENCY)
				.cost(ZERO.intValue())
				.tax(ZERO.byteValue())
				.name("Wyspy Lenistwa")
				.build();
	}

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private IncomeCountryRepository incomeCountryRepository;
}
