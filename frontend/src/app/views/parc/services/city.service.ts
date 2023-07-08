import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { City } from './../models/city.model'
import { tap } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CityService {


  apiUrl = 'http://localhost:8081/tunCamp/cities';


  constructor(private http: HttpClient) { }

  getAllCity(): Observable<City[]> {
    const url = `${this.apiUrl}`;

    return this.http.get<City[]>(url).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('An error occurred:', error);
        return throwError('Something went wrong. Please try again later.');
      })
    );
  }
}
