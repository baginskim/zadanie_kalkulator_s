import {async, TestBed} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {SalaryFormComponent} from './salary-form/salary-form.component';
import {IncomeDisplayComponent} from './income-display/income-display.component';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        AppComponent,
        SalaryFormComponent,
        IncomeDisplayComponent
      ], imports: [BrowserModule,
        FormsModule,
        HttpClientModule]
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'income-calculator-front'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('income-calculator-front');
  });

  it('should render title in a h4 tag', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h4').textContent).toContain('Type your gross salary, select country and press calculate!');
  });
});
