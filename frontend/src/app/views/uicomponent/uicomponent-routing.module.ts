import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EcommerceComponent } from './ecommerce/ecommerce.component';
import { ParcsComponent } from './parcs/parcs.component';
import { ReservationComponent } from './reservation/reservation.component';
import { SocialComponent } from './social/social.component';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'tuniCamp',
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'home',
      },
      {
        path: 'home',
        component: HomeComponent,
        data: {
          title: 'Home',
        },
      },
      {
        path: 'social',
        component: SocialComponent,
        data: {
          title: 'Social',
        },
      },
      {
        path: 'users',
        component: UsersComponent,
        data: {
          title: 'Users',
        },
      },
      {
        path: 'reservation',
        component: ReservationComponent,
        data: {
          title: 'Reservation',
        },
      },
      {
        path: 'parcs',
        component: ParcsComponent,
        data: {
          title: 'Parcs',
        },
      },
      {
        path: 'ecommerce',
        component: EcommerceComponent,
        data: {
          title: 'Ecommerce',
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UIcomponentRoutingModule { }
