package com.baginskim.incomecalculator;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
class IncomeCountry {

	private final byte tax;

	private final String currency;

	private final int cost;

}
