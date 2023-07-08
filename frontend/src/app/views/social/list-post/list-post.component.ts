import { Component } from '@angular/core';
import { PostDTO } from '../models/PostDTO.model';
import { PostService } from '../services/post.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-list-post',
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.scss']
})
export class ListPostComponent {
  posts:PostDTO[];
  constructor(private postService: PostService) {
    this.posts=[]
  }
  ngOnInit(): void {
    this.getPosts();

  }
  public getPosts(): void{
    this.postService.getPost().subscribe(
      (response: PostDTO[]) =>{
        this.posts = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public deletePost(idPost:number): void{
    console.log(idPost);
    this.postService.deletePost(idPost).subscribe(
      response =>{
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
