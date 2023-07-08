import { Component } from '@angular/core';
import { CommentDTO } from '../models/CommentDTO.model';
import { CommentService } from '../services/comment.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-list-reactions',
  templateUrl: './list-reactions.component.html',
  styleUrls: ['./list-reactions.component.scss']
})
export class ListReactionsComponent {
  comments:CommentDTO[];
  constructor(private commentService: CommentService) {
    this.comments=[]
  }
  ngOnInit(): void {
    this.getComments();

  }
  public getComments(): void{
    this.commentService.getComment().subscribe(
      (response: CommentDTO[]) =>{
        this.comments = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public deleteComment(id:number): void{
    console.log(id);
    this.commentService.deleteComment(id).subscribe(
      response =>{
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
