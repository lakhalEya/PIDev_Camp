import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParcRoutingModule } from './parc-routing.module';
import { EquipmentComponent } from './equipment/equipment.component';
import { ActivityComponent } from './activity/activity.component';
import { ParcComponent } from './parc/parc.component';




@NgModule({
  declarations: [
    EquipmentComponent,
    ActivityComponent,
    ParcComponent
  ],
  imports: [
    CommonModule,
    ParcRoutingModule
  ]
})
export class ParcModule { }
