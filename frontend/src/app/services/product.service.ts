import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from 'src/model/Product';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }


  
  private baseUrl = "http://127.0.0.1:8081/api";



  createProduct(product: Product): Observable<any> {
    return this.http.post(this.baseUrl + "/Product", product);
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + "/Products");
  }

  addProduct(id: number, idstat: number): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + "/Product");
  }

  deleteProduct(id: number): Observable<Product> {
    return this.http.delete<Product>(`${this.baseUrl}/Product/${id}`);
  }

  findProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.baseUrl}/Product/${id}`);
  }

  updateProduct(product: Product, id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/Product/${id}`, product);
  }
}
