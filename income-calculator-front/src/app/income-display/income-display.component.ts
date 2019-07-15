import {Component, Input, OnInit} from '@angular/core';
import {IncomeCountry} from '../income-country';

@Component({
  selector: 'app-income-display',
  templateUrl: './income-display.component.html',
  styleUrls: ['./income-display.component.css']
})
export class IncomeDisplayComponent implements OnInit {

  @Input() income: number;
  @Input() selectedCountry: IncomeCountry;

  constructor() {
  }

  ngOnInit() {
  }

}
