import {Component, OnInit} from '@angular/core';
import {IncomeCountryService} from '../income-country.service';
import {IncomeCountry} from '../income-country';
import {IncomeService} from '../income.service';

@Component({
  selector: 'app-salary-form',
  templateUrl: './salary-form.component.html',
  styleUrls: ['./salary-form.component.css']
})
export class SalaryFormComponent implements OnInit {

  private countries: IncomeCountry[];
  private grossSalary: number;
  private income: number;
  private selectedCountryId: number;

  constructor(private incomeCountryService: IncomeCountryService, private incomeService: IncomeService) {
  }

  ngOnInit() {
    this.incomeCountryService.getCountries().subscribe(countires => this.countries = countires);
  }

  calculate() {
    if (this.grossSalary !== undefined && this.selectedCountryId !== undefined) {
      this.incomeService.getIncome(this.selectedCountryId, this.grossSalary).subscribe(income => this.income = income);
    }
  }

  getSelectedCountry() {
    return this.countries.find(el =>  el.id == this.selectedCountryId);
  }
}
