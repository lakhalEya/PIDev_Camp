import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CartItem } from 'src/model/CartItem';

@Injectable({
  providedIn: 'root'
})
export class CartItemservice {

  constructor(private http: HttpClient) { }


  
  private baseUrl = "http://127.0.0.1:8081/api";



  createitem(item: CartItem, id: number): Observable<any> {
    return this.http.post(this.baseUrl + "/item/"+id, item);
  }

  getitems(): Observable<CartItem[]> {
    return this.http.get<CartItem[]>(this.baseUrl + "/items");
  }

  getitem(id: number): Observable<CartItem[]> {
    return this.http.get<CartItem[]>(this.baseUrl + "/item/${id}");
  }

  deleteitem(id: number): Observable<CartItem> {
    return this.http.delete<CartItem>(`${this.baseUrl}/item/${id}`);
  }

  finditemById(id: number): Observable<CartItem> {
    return this.http.get<CartItem>(`${this.baseUrl}/item/${id}`);
  }

  updateitem(item: CartItem, id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/item`, item);
  }
}
