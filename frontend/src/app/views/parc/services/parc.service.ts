import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parc } from './../models/parc.model'
import { tap } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ParcService {

  apiUrl = 'http://localhost:8081/tunCamp/parcs';


  constructor(private http: HttpClient) { }

  getAllParcs(): Observable<Parc[]> {
    const url = `${this.apiUrl}`;
    return this.http.get<Parc[]>(url).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('An error occurred:', error);
        return throwError('Something went wrong. Please try again later.');
      })
    );
  }
}
