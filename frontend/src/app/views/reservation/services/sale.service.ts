import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SaleService {


  public API = 'http://localhost:8081/tunCamp/sales';
  constructor(private https : HttpClient) { }

  getAll(): Observable<any> {
    return this.https.get(this.API+'/all');
  }


  deleteSale(saleId: number): Observable<{ code: number, message: string }> {
    const url = this.API+'/delete/'+saleId;
    return this.https.delete(url, { observe: 'response', responseType: 'text' }).pipe(
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
