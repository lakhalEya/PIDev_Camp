import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ForumComponent } from './forum/forum.component';
import { SocialRoutingModule } from './social-routing.module';
import { PostComponent } from './post/post.component';
import { ReactionComponent } from './reaction/reaction.component'



@NgModule({
  declarations: [
    ForumComponent,
    PostComponent,
    ReactionComponent
  ],
  imports: [
    CommonModule,
    SocialRoutingModule
  ]
})
export class SocialModule { }
