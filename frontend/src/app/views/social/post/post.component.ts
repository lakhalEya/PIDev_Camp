import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostDTO } from '../models/PostDTO.model';
import { PostService } from './../services/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent {
  form: FormGroup;
  posts:PostDTO[];
  post:PostDTO;
  postId:number;
  constructor(private postService: PostService,private formBuilder: FormBuilder,private route: ActivatedRoute,private router: Router) {
    this.posts=[]

    this.form = this.formBuilder.group({
      // Define your form fields and validators
      title: ['', Validators.required],
      content: ['', Validators.required],
      category: ['', Validators.required],
      fileName: ['', Validators.required],
      datePublication: ['', Validators.required],
      visibility: ['', Validators.required],
      communitySpaceId: [this.getForumId(), Validators.required],
      // Add morte fields as needed
    });
  }
  getForumId() {
    return  this.route.snapshot.paramMap.get('value');
  }
  public addPost(): void{
    this.post = this.form.value;

    console.log(this.post);
    this.postService.addPost(this.post).subscribe(
      response =>{
        this.router.navigateByUrl('/social/listPosts');

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public deleteCommunitySpace(idForum:number): void{


    console.log(idForum);
    /*this.communitySpaceService.deleteCommunitySpace(idForum).subscribe(
      response =>{
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );*/
  }
}



