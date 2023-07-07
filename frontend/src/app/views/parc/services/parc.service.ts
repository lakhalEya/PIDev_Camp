import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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

  compareParcs(
    parcIds: number[],
    minRating?: number,
    amenities?: string[],
    city?: string,
    country?: string,
    category?: string,
    minCapacity?: number
  ): Observable<Parc[]> {
    const params = new HttpParams()
      .set('parcIds', parcIds.join(','))
      .set('minRating', minRating ? minRating.toString() : '')
      .set('amenities', amenities ? amenities.join(',') : '')
      .set('city', city || '')
      .set('country', country || '')
      .set('category', category || '')
      .set('minCapacity', minCapacity ? minCapacity.toString() : '');
      const url = `${this.apiUrl}/compare`;

    return this.http.get<Parc[]>(url, { params });
  }

  getMinRatingRange(parcIds: number[]): Observable<number> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/compare/minRatings`;

    return this.http.get<number>(url, { params });
  }

  getMaxRatingRange(parcIds: number[]): Observable<number> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/compare/maxRatings`;

    return this.http.get<number>(url, { params });
  }

  getMinCapacityRange(parcIds: number[]): Observable<number> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/compare/minCapacity`;

    return this.http.get<number>(url, { params });
  }

  getMaxCapacityRange(parcIds: number[]): Observable<number> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/compare/maxCapacity`;

    return this.http.get<number>(url, { params });
  }

  getAllAmenities(parcIds: number[]): Observable<string[]> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/amenities`;

    return this.http.get<string[]>(url, { params });
  }

  getAllCities(parcIds: number[]): Observable<string[]> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/cities`;

    return this.http.get<string[]>(url, { params });
  }

  getAllCountries(parcIds: number[]): Observable<string[]> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/countries`;

    return this.http.get<string[]>(url, { params });
  }

  getAllCategories(parcIds: number[]): Observable<string[]> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/categories`;

    return this.http.get<string[]>(url, { params });
  }
}


