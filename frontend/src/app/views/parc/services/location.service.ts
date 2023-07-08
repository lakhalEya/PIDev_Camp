import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Location } from './../models/location.model'
import { tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class LocationService {

  apiUrl = 'http://localhost:8081/tunCamp/locations';

  constructor(private http: HttpClient) { }

  getLocationById(id: number): Observable<Location> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Location>(url);
  }

  getAllLocations(): Observable<Location[]> {
    const url = `${this.apiUrl}`;
    return this.http.get<Location[]>(url).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('An error occurred:', error);
        return throwError('Something went wrong. Please try again later.');
      })
    );
  }

  deleteLocation(locationId: number): Observable<{ code: number, message: string }> {
    const url = `${this.apiUrl}/${locationId}`;
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
