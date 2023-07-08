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

  searchParcByKeyword(keyword: string): Observable<Parc[]> {
    return this.http.get<Parc[]>(`${this.apiUrl}/search/${keyword}/DISABLED`);
  }

  getAllParcAmenties(parcId: number): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/parcamenities/${parcId}`);
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
    amenities?: string,
    city?: string,
    country?: string,
    category?: string,
    minCapacity?: number
  ): Observable<Parc[]> {
    let params = new HttpParams().set('parcIds', parcIds.join(','));
    if (minRating) {
      params = params.set('minRating', minRating.toString());
    }
    if (amenities && amenities.trim() !== '') {
      params = params.set('amenities', amenities);
    }
    if (city && city.trim() !== '') {
      params = params.set('city', city);
    }
    if (country && country.trim() !== '') {
      params = params.set('country', country);
    }
    if (category && category.trim() !== '') {
      params = params.set('category', category);
    }
    if (minCapacity) {
      params = params.set('minCapacity', minCapacity.toString());
    }

    const url = `${this.apiUrl}/compare`;

    return this.http.get<Parc[]>(url, { params });
  }

  getMinRatingRange(parcIds: number[]): Observable<number> {
    const params = new HttpParams().set('parcIds', parcIds.join(','));
    const url = `${this.apiUrl}/compare/minRatings`;

    return this.http.get<number>(url, { params });
  }

  getParcsAmenities(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/allamenities`);
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

  findParcByDisponibility(status: string): Observable<Parc[]> {
    const url = `${this.apiUrl}/availabe/${status}`;
    return this.http.get<Parc[]>(url);
  }

  findParcByCategory(category: string): Observable<Parc[]> {
    const url = `${this.apiUrl}/category/${category}`;
    return this.http.get<Parc[]>(url);
  }

  enableParc(id: number): Observable<Parc> {
    console.log("id:",id)
    const url = `${this.apiUrl}/enable/${id}`;
    return this.http.put<Parc>(url, null);
  }
  disableParc(id: number): Observable<Parc> {
    const url = `${this.apiUrl}/disable/${id}`;
    return this.http.put<Parc>(url, null);
  }
  updateParc(parcId: number, parc: Parc): Observable<Parc> {
    const url = `${this.apiUrl}/${parcId}`;
    return this.http.post<Parc>(url, parc);
  }
  createParc(parc: Parc): Observable<Parc> {
    return this.http.post<Parc>(`${this.apiUrl}`, parc);
  }


}


