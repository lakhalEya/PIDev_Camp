import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { PostDTO } from '../../social/models/PostDTO.model'
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl = 'http://localhost:8081/tunCamp/post';
  constructor(private http: HttpClient) { }
  public getPost(): Observable<PostDTO[]>{
    return this.http.get<PostDTO[]>(this.apiUrl + "/show");
  }

  public addPost(postDTO:PostDTO): Observable<PostDTO>{
    return this.http.post<PostDTO>(this.apiUrl + "/addPost",postDTO);
  }

  public updatePost(postDTO:PostDTO): Observable<PostDTO>{
    return this.http.put<PostDTO>(this.apiUrl + "/update",postDTO);
  }

  public deletePost(idForum:number): Observable<any>{
    return this.http.delete<any>(this.apiUrl + `/delete/${idForum}`);
  }
}
