import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from 'src/model/Cart';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }


  
  private baseUrl = "http://127.0.0.1:8081/api";



  createCart(Cart: Cart): Observable<any> {
    return this.http.post(this.baseUrl + "/Cart", Cart);
  }

  getCarts(): Observable<Cart[]> {
    return this.http.get<Cart[]>(this.baseUrl + "/Carts");
  }

 
  deleteCart(id: number): Observable<Cart> {
    return this.http.delete<Cart>(`${this.baseUrl}/Cart/${id}`);
  }

  findCartById(id: number): Observable<Cart> {
    return this.http.get<Cart>(`${this.baseUrl}/Cart/${id}`);
  }

  updateCart(Cart: Cart, id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/Cart`, Cart);
  }
}
