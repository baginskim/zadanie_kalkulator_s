package com.baginskim.incomecalculator;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
class IncomeCountry {

	@Id
	private Long id;

	private byte tax;

	private String currency;

	private int cost;

	private String name;
}
