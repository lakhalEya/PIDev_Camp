import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CommentDTO } from '../models/CommentDTO.model';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = 'http://localhost:8081/tunCamp/comment';
  constructor(private http: HttpClient) { }
  public getComment(): Observable<CommentDTO[]>{
    return this.http.get<CommentDTO[]>(this.apiUrl + "/show");
  }

  public addComment(commDTO:CommentDTO): Observable<CommentDTO>{
    return this.http.post<CommentDTO>(this.apiUrl + "/addComment",commDTO);
  }

  public updateComment(commDTO:CommentDTO): Observable<CommentDTO>{
    return this.http.put<CommentDTO>(this.apiUrl + "/update",commDTO);
  }

  public deleteComment(idComment:number): Observable<any>{
    return this.http.delete<any>(this.apiUrl + `/delete/${idComment}`);
  }
}
