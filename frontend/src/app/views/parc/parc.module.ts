import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParcRoutingModule } from './parc-routing.module';
import { EquipmentComponent } from './equipment/equipment.component';
import { ActivityComponent } from './activity/activity.component';
import { ParcComponent } from './parc/parc.component';

import { ReactiveFormsModule } from '@angular/forms';

import { DocsComponentsModule } from '@docs-components/docs-components.module';


import {
  ButtonGroupModule,
  ButtonModule,
  CardModule,
  CollapseModule,
  DropdownModule,
  FormModule,
  GridModule,
  NavbarModule,
  NavModule,
  SharedModule,
  UtilitiesModule
} from '@coreui/angular';

import { IconModule } from '@coreui/icons-angular';






@NgModule({
  declarations: [
    EquipmentComponent,
    ActivityComponent,
    ParcComponent,
  ],
  imports: [
    CommonModule,
    ParcRoutingModule,
    CommonModule,
    ButtonModule,
    ButtonGroupModule,
    GridModule,
    IconModule,
    CardModule,
    UtilitiesModule,
    DropdownModule,
    SharedModule,
    FormModule,
    ReactiveFormsModule,
    DocsComponentsModule,
    NavbarModule,
    CollapseModule,
    NavModule,
    NavbarModule
  ]
})
export class ParcModule { }
