import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForumComponent } from './forum/forum.component';
import { PostComponent } from './post/post.component';
import { ReactionComponent } from './reaction/reaction.component';


const routes: Routes = [
  {
    path: '',
    data: {
      title: 'social',
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'forums',
      },
      {
        path: 'forums',
        component: ForumComponent,
        data: {
          title: 'forums',
        },
      },
      {
        path: 'posts',
        component: PostComponent,
        data: {
          title: 'posts',
        },
      },
      {
        path: 'reactions',
        component: ReactionComponent,
        data: {
          title: 'reactions',
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SocialRoutingModule {}

