package com.baginskim.incomecalculator;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IncomeCountryRepository extends CrudRepository<IncomeCountry, Long> {

}
