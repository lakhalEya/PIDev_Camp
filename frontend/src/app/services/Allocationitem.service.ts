import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Allocationitem } from './../../model/Allocationitem';

@Injectable({
  providedIn: 'root'
})
export class AllocationitemService {

  constructor(private http: HttpClient) { }


  
  private baseUrl = "http://127.0.0.1:8081/api";



  createlocitem(locitem: Allocationitem, id: number): Observable<any> {
    return this.http.post(this.baseUrl + "/locitem/"+id, locitem);
  }

  getlocitems(): Observable<Allocationitem[]> {
    return this.http.get<Allocationitem[]>(this.baseUrl + "/locitems");
  }

  

  deletelocitem(id: number): Observable<Allocationitem> {
    return this.http.delete<Allocationitem>(`${this.baseUrl}/locitem/${id}`);
  }

  findlocitemById(id: number): Observable<Allocationitem> {
    return this.http.get<Allocationitem>(`${this.baseUrl}/locitem/${id}`);
  }

  updatelocitem(locitem: Allocationitem, id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/locitem`, locitem);
  }
}
