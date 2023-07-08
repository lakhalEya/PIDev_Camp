import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { Reservation } from '../models/reservation';
import { Sale } from '../models/sale';



@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  public API = 'http://localhost:8081/tunCamp/reservations';
  constructor(private https : HttpClient) { }

  getAll(): Observable<any> {
    return this.https.get<Reservation[]>(this.API+'/all').pipe(
      map(response=>response)
    );
  }

  assignSale(reservation: Reservation,sale : Sale): Observable<Reservation> {
    const url = this.API+'/affectSale/'+reservation.id+'/'+sale.id;
    return this.https.put<Reservation>(url, reservation);
  }

  addForParc(reservation : Reservation ): Observable<Reservation> {
    //recupérer l'id du parc 
    reservation.user=1;
    return this.https.post<Reservation>(this.API+'/addForParc/'+1,reservation);
  }

  getNbReservation(): Observable<number> {
    return this.https.get<number>(this.API+'/nbReservation');
  }
  getNbReservationParc(): Observable<number> {
    return this.https.get<number>(this.API+'/nbReservationParc');
  }

  getNbReservationActivity(): Observable<number> {
    return this.https.get<number>(this.API+'/nbReservationActivity');
}


deleteReservation(resId: number): Observable<{ code: number, message: string }> {
  const url = this.API+'/delete/'+resId;
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

cancelReservation(resId: number): Observable<{ code: number, message: string }> {
  const url = this.API+'/cancel/'+resId;
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
