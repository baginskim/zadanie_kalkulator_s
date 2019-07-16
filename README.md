# Income calculator

# Required libs/tools
JDK 8, Maven, Node 10.9+, Angular 8.1+

# Development - server
Run `IncomeCalculatorApplication` with profile `local-dev` from IDE
or
`mvn spring-boot:run -Dspring.profiles.active="local-dev"` in `income-calculator-server` directory

# Development - server - status
`curl http://[server.host]:[server.port]/actuator/health` e.g. `curl http://localhost:8080/actuator/health`


# Development - front
run `ng serve --open`

# Using rest API
### get all available countries
`curl http://[server.host]:[server.port]/countries` e.g. `curl http://localhost:8080/countries`

### get monthly income
`curl http://[server.host]:[server.port]/income/[countryId]/[dailyGrossSalary]` e.g. `curl http://localhost:8080/income/1/850`

