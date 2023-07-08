import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommunitySpaceDTO } from '../../social/models/CommunitySpaceDTO.model';


@Injectable({
  providedIn: 'root'
})
export class CommunitySpaceService {
  private apiUrl = 'http://localhost:8081/tunCamp/community-spaces';

  constructor(private http: HttpClient) {   }
  public getCommunitySpace(): Observable<CommunitySpaceDTO[]>{
    return this.http.get<CommunitySpaceDTO[]>(this.apiUrl + "/show");
  }

  public addCommunitySpace(communitySpaceDTO:CommunitySpaceDTO): Observable<CommunitySpaceDTO>{
    return this.http.post<CommunitySpaceDTO>(this.apiUrl + "/add",communitySpaceDTO);
  }

  public updateCommunitySpace(communitySpaceDTO:CommunitySpaceDTO): Observable<CommunitySpaceDTO>{
    return this.http.put<CommunitySpaceDTO>(this.apiUrl + "/update",communitySpaceDTO);
  }

  public deleteCommunitySpace(idForum:number): Observable<any>{
    return this.http.delete<any>(this.apiUrl + `/delete/${idForum}`);
  }
}
