import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class StaticsService {

  apiUrl = 'http://localhost:8081/tunCamp/statistics';


  constructor(private http: HttpClient) { }


  getEnabledParcCount(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/count/enabled`);
  }

  getParcCount(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/count`);
  }

  getMostUsedCategory(): Observable<string> {
    const url = `${this.apiUrl}/category/most-used`;
    return this.http.get(url, { observe: 'response', responseType: 'text' }).pipe(
      map(response => response.body as string),
      catchError((error: HttpErrorResponse) => {
        console.error('An error occurred:', error);
        return throwError('Something went wrong. Please try again later.');
      })
    );
  }
}
