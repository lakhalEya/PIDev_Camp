import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationComponent } from './reservation/reservation.component';
import { PromotionComponent } from './promotion/promotion.component';



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
        },
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

