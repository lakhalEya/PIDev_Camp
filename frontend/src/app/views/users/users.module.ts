import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRoutingModule } from './user-routing.module';
import { UsermanagementComponent } from './usermanagement/usermanagement.component';
import { ReclamationComponent } from './reclamation/reclamation.component'



@NgModule({
  declarations: [
    UsermanagementComponent,
    ReclamationComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UsersModule { }
