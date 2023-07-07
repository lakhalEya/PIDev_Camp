import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SaleService {


  public API = '//localhost:8081/sales';
  constructor(private https : HttpClient) { }

  getAll(): Observable<any> {
    return this.https.get(this.API+'/all');
  }
}
