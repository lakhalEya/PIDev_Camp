import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Reservation } from 'src/app/models/booking/reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  public API = 'http://localhost:8082/tunCamp/reservations';
  constructor(private https : HttpClient) { }

  getAll(): Observable<any> {
    return this.https.get<Reservation[]>(this.API+'/all').pipe(
      map(response=>response)
    );
  }
}
