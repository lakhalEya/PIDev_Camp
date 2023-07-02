import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from 'src/model/Order';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Orderservice {

  constructor(private http: HttpClient) { }


  
  private baseUrl = "http://127.0.0.1:8081/api";



  createOrder(Order: Order,id:number): Observable<any> {
    return this.http.post(this.baseUrl + "/Order/"+id, Order);
  }

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.baseUrl + "/Orders");
  }

  addOrder(id: number, idstat: number): Observable<Order[]> {
    return this.http.get<Order[]>(this.baseUrl + "/Order");
  }

  deleteOrder(id: number): Observable<Order> {
    return this.http.delete<Order>(`${this.baseUrl}/Order/${id}`);
  }

  findOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.baseUrl}/Order/${id}`);
  }

  updateOrder(Order: Order, id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/Order`, Order);
  }
}
