import {Injectable} from '@angular/core';
import {IncomeCountry} from './income-country';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IncomeCountryService {

  private countriesUrl = 'http://localhost:8080/countries';  // URL to web api

  getCountries(): Observable<IncomeCountry[]> {
    return this.http.get<IncomeCountry[]>(this.countriesUrl)
      .pipe(
        tap(_ => this.log('fetched countries')),
        catchError(this.handleError<IncomeCountry[]>('getCountries', []))
      );
  }

  private log(message: string) {
    console.log(`IncomeCountryService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  constructor(
    private http: HttpClient) {
  }
}
