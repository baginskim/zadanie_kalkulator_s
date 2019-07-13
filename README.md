# Income calculator

# Required libs/tools
JDK 8, Maven

# Development - server
Run `IncomeCalculatorApplication` from IDE
or
`mvn spring-boot:run` in `income-calculator-server` directory

# Development - server - status
`curl http://[server.host]:[server.port]/actuator/health` e.g. `curl http://localhost:8080/actuator/health`


# Development - front
TODO marek.baginski

#Using
### get all available countries
`curl http://[server.host]:[server.port]/countries` e.g. `curl http://localhost:8080/countries`

### get monthly income
`curl http://[server.host]:[server.port]/income/[countryId]/[dailyGrossSalary]` e.g. `curl http://localhost:8080/income/1/850`

