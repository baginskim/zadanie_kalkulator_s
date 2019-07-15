import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  private incomeUrl = 'http://localhost:8080/income/';  // URL to web api

  getIncome(countryId: number, grossSalary: number): Observable<number> {
    return this.http.get<number>(this.incomeUrl + countryId + '/' + grossSalary)
      .pipe(
        tap(_ => this.log('fetched income')),
        catchError(this.handleError<number>('getIncome', null))
      );
  }

  private log(message: string) {
    console.log(`IncomeService: ${message}`);
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
