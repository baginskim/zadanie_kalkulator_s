import {TestBed} from '@angular/core/testing';

import {IncomeService} from './income.service';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

describe('IncomeService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [BrowserModule,
      FormsModule,
      HttpClientModule]
  }));

  it('should be created', () => {
    const service: IncomeService = TestBed.get(IncomeService);
    expect(service).toBeTruthy();
  });
});
