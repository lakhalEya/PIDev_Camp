import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForumComponent } from './forum/forum.component';
import { PostComponent } from './post/post.component';
import { ReactionComponent } from './reaction/reaction.component';
import { ListPostComponent } from './list-post/list-post.component';
import { ListReactionsComponent } from './list-reactions/list-reactions.component';


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
        path: 'listPosts',
        component: ListPostComponent,
        data: {
          title: 'listPosts',
        },
      },
      {
        path: 'reactions/:value',
        component: ReactionComponent,
        data: {
          title: 'reactions',
        },
      },
      {
        path: 'reactions',
        component: ReactionComponent,
        data: {
          title: 'reactions',
        },
      },
      {
        path: 'posts/:value',
        component: PostComponent,
        data: {
            title: 'posts',
        },
     },
     {
      path: 'listReactions',
      component: ListReactionsComponent,
      data: {
        title: 'listReactions',
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

