import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SalaryFormComponent } from './salary-form/salary-form.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { IncomeDisplayComponent } from './income-display/income-display.component';

@NgModule({
  declarations: [
    AppComponent,
    SalaryFormComponent,
    IncomeDisplayComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
