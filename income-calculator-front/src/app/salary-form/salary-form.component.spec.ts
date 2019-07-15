import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SalaryFormComponent} from './salary-form.component';
import {FormsModule} from '@angular/forms';
import {IncomeDisplayComponent} from '../income-display/income-display.component';
import {IncomeCountryService} from '../income-country.service';
import {IncomeService} from '../income.service';
import {HttpClientModule} from '@angular/common/http';


describe('SalaryFormComponent', () => {
  let component: SalaryFormComponent;
  let fixture: ComponentFixture<SalaryFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SalaryFormComponent, IncomeDisplayComponent],
      imports: [FormsModule, HttpClientModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalaryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
