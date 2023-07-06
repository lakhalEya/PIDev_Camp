import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parc } from './../models/parc.model'
import { tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

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

  deleteParc(parcId: number): Observable<{ code: number, message: string }> {
    const url = `${this.apiUrl}/${parcId}`;
    return this.http.delete(url, { observe: 'response', responseType: 'text' }).pipe(
      map(response => {
        return { code: response.status, message: response.body as string };
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('An error occurred:', error);
        return throwError('Something went wrong. Please try again later.');
      })
    );
  }

}
