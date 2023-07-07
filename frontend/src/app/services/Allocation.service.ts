import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Allocation } from './../../model/Allocation';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AllocationService {

  constructor(private http: HttpClient) { }



  private baseUrl = "http://localhost:8081/tunCamp/api";



  createAllocation(Allocation: Allocation): Observable<any> {
    return this.http.post(this.baseUrl + "/Allocation", Allocation);
  }

  getAllocations(): Observable<Allocation[]> {
    return this.http.get<Allocation[]>(this.baseUrl + "/Allocations");
  }


  deleteAllocation(id: number): Observable<Allocation> {
    return this.http.delete<Allocation>(`${this.baseUrl}/Allocation/${id}`);
  }

  findAllocationById(id: number): Observable<Allocation> {
    return this.http.get<Allocation>(`${this.baseUrl}/Allocation/${id}`);
  }

  updateAllocation(Allocation: Allocation): Observable<any> {
    return this.http.put(`${this.baseUrl}/Allocation`, Allocation);
  }
}
