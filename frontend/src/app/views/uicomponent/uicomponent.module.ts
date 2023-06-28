import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UIcomponentRoutingModule } from './uicomponent-routing.module';
import { UsersComponent } from './users/users.component';
import { ParcsComponent } from './parcs/parcs.component';
import { SocialComponent } from './social/social.component';
import { EcommerceComponent } from './ecommerce/ecommerce.component';
import { ReservationComponent } from './reservation/reservation.component';
import { HomeComponent } from './home/home.component';

import { CarouselModule } from '@coreui/angular';

@NgModule({
  declarations: [
    UsersComponent,
    ParcsComponent,
    SocialComponent,
    EcommerceComponent,
    ReservationComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    CarouselModule,
    UIcomponentRoutingModule
  ]
})
export class UIcomponentModule { }
