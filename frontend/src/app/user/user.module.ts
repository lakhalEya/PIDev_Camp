import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserLayoutComponent } from './user-layout/user-layout.component';
import { SharedModule } from '../shared/shared.module';
import { RouterModule  } from '@angular/router';



@NgModule({
  declarations: [
    UserLayoutComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModule,
    RouterModule,

  ]
})
export class UserModule { }
