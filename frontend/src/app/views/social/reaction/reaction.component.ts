import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentDTO } from '../models/CommentDTO.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentService } from '../services/comment.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-reaction',
  templateUrl: './reaction.component.html',
  styleUrls: ['./reaction.component.scss']
})
export class ReactionComponent {
  form: FormGroup;
  comment:CommentDTO;
  commentId:number;
  constructor(private commentService: CommentService,private formBuilder: FormBuilder,private route: ActivatedRoute,private router: Router) {
    

    this.form = this.formBuilder.group({
      // Define your form fields and validators
      content: ['', Validators.required],
      datePublication: ['', Validators.required],
      postId: [this.getPostId(), Validators.required],
      // Add morte fields as needed
    });
  }
  getPostId() {
    return  this.route.snapshot.paramMap.get('value');
  }
  public addComment(): void{
    this.comment = this.form.value;

    console.log(this.comment);
    this.commentService.addComment(this.comment).subscribe(
      response =>{
        this.router.navigateByUrl('/social/listReactions');

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
