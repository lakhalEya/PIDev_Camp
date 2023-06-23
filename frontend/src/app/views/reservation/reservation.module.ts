import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservationRoutingModule } from './reservation-routing.module';
import { PromotionComponent } from './promotion/promotion.component';
import { ReservationComponent } from './reservation/reservation.component';



@NgModule({
  declarations: [
    PromotionComponent,
    ReservationComponent
  ],
  imports: [
    CommonModule,
    ReservationRoutingModule
  ]
})
export class ReservationModule { }
