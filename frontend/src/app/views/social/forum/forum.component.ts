import { Component, OnInit } from '@angular/core';
import { CommunitySpaceDTO } from '../models/CommunitySpaceDTO.model';
import { HttpErrorResponse } from '@angular/common/http';
import { CommunitySpaceService } from './../services/community-space.service';
import { getStyle } from '@coreui/utils';
import { ChartjsComponent } from '@coreui/angular-chartjs';
import { freeSet } from '@coreui/icons';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.scss']
})
export class ForumComponent implements OnInit {
  public communitySpaces: CommunitySpaceDTO[];
  public idForum: number;
  form: FormGroup;
  onFileSelected($event: Event) {
  throw new Error('Method not implemented.');
  }
  communitySpace: CommunitySpaceDTO;
  errorMessage: string;

  constructor(private communitySpaceService: CommunitySpaceService,private formBuilder: FormBuilder,private router: Router) {
    this.communitySpaces=[]
    this.form = this.formBuilder.group({
      // Define your form fields and validators
      title: ['', Validators.required],
      description: ['', Validators.required],
      category: ['', Validators.required],
      fileName: ['', Validators.required],
      // Add morte fields as needed
    });
  }

  ngOnInit(): void {
    this.getCommunitySpace();
    this.communitySpace = new CommunitySpaceDTO();
    // Initialize any other properties as needed
  }
  public getCommunitySpace(): void{
    this.communitySpaceService.getCommunitySpace().subscribe(
      (response: CommunitySpaceDTO[]) =>{
        this.communitySpaces = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public visible = false;

  toggleLiveDemo() {
    this.visible = !this.visible;
  }

  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

  public addCommunitySpace(): void{
    this.communitySpace = this.form.value;

    console.log(this.communitySpace);
    this.communitySpaceService.addCommunitySpace(this.communitySpace).subscribe(
      response =>{
        this.idForum = response.idForum;
        console.log(this.idForum);
        this.toggleLiveDemo();
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public deleteCommunitySpace(idForum:number): void{


    console.log(idForum);
    this.communitySpaceService.deleteCommunitySpace(idForum).subscribe(
      response =>{
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

 
}
