import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParcRoutingModule } from './parc-routing.module';
import { EquipmentComponent } from './equipment/equipment.component';
import { ActivityComponent } from './activity/activity.component';
import { ParcComponent } from './parc/parc.component';
import { FormsModule } from '@angular/forms';

import { ReactiveFormsModule } from '@angular/forms';

import { DocsComponentsModule } from '@docs-components/docs-components.module';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';


import {
  AccordionModule,
  BadgeModule,
  BreadcrumbModule,
  ButtonModule,
  CardModule,
  CarouselModule,
  CollapseModule,
  DropdownModule,
  FormModule,
  GridModule,
  ListGroupModule,
  NavModule,
  PaginationModule,
  PlaceholderModule,
  PopoverModule,
  ProgressModule,
  SharedModule,
  SpinnerModule,
  TableModule,
  TabsModule,
  TooltipModule,
  UtilitiesModule,
  ButtonGroupModule,
  NavbarModule,



} from '@coreui/angular';

import { IconModule } from '@coreui/icons-angular';






@NgModule({
  declarations: [
    EquipmentComponent,
    ActivityComponent,
    ParcComponent
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    AccordionModule,
    BadgeModule,
    BreadcrumbModule,
    ButtonModule,
    CardModule,
    CarouselModule,
    CollapseModule,
    DropdownModule,
    FormModule,
    GridModule,
    ListGroupModule,
    NavModule,
    PaginationModule,
    PlaceholderModule,
    PopoverModule,
    ProgressModule,
    SharedModule,
    SpinnerModule,
    TableModule,
    TabsModule,
    TooltipModule,
    UtilitiesModule,
    CommonModule,
    ParcRoutingModule,
    ButtonGroupModule,
    IconModule,
    ReactiveFormsModule,
    DocsComponentsModule,
    NavbarModule,
    NgxPaginationModule

  ]
})
export class ParcModule { }
