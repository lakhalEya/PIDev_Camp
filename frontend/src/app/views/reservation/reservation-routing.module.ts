import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationComponent } from './reservation/reservation.component';
import { PromotionComponent } from './promotion/promotion.component';
import { ListReservationsComponent } from './components/backoffice/list-reservations/list-reservations.component';

import { AddReservationComponent } from './components/frontoffice/add-reservation/add-reservation.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'reservation',
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'forums',
      },
      {
        path: 'reservation',
        component: ReservationComponent,
        data: {
          title: 'reservation',
        }
      },
      {
        path: 'list-reservations',
            component: ListReservationsComponent,
            data: {
              title: 'listreservations',
            }
      },
      {
        path: 'add-reservations',
        component: AddReservationComponent,
        data: { 
          title: 'addreservations',
        }
      },
      {
        path: 'pricing-promotion',
        component: PromotionComponent,
        data: {
          title: 'Pricing and Promotion',
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReservationRoutingModule {}

