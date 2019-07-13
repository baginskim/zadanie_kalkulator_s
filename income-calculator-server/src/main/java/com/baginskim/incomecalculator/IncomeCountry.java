package com.baginskim.incomecalculator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
class IncomeCountry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private final byte tax;

	private final String currency;

	private final int cost;

	private final String name;
}
