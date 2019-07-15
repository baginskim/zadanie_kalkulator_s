import {TestBed} from '@angular/core/testing';

import {IncomeCountryService} from './income-country.service';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

describe('IncomeCountryService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [BrowserModule,
      FormsModule,
      HttpClientModule]
  }));

  it('should be created', () => {
    const service: IncomeCountryService = TestBed.get(IncomeCountryService);
    expect(service).toBeTruthy();
  });
});
