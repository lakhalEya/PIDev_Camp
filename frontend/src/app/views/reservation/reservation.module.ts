import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservationRoutingModule } from './reservation-routing.module';
import { PromotionComponent } from './promotion/promotion.component';
import { ReservationComponent } from './reservation/reservation.component';
import { ListReservationComponent } from 'src/app/components/booking/list-reservation/list-reservation.component';



@NgModule({
  declarations: [
    PromotionComponent,
    ReservationComponent,
    ListReservationComponent
  ],
  imports: [
    CommonModule,
    ReservationRoutingModule
  ]
})
export class ReservationModule { }
