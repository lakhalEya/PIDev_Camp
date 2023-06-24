import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsermanagementComponent } from './usermanagement/usermanagement.component';
import { ReclamationComponent } from './reclamation/reclamation.component';



const routes: Routes = [
  {
    path: '',
    data: {
      title: 'user',
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'forums',
      },
      {
        path: 'user-management',
        component: UsermanagementComponent,
        data: {
          title: 'user-management',
        },
      },
      {
        path: 'reclamations',
        component: ReclamationComponent,
        data: {
          title: 'reclamations',
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}

