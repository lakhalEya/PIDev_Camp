import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParcRoutingModule } from './parc-routing.module';
import { EquipmentComponent } from './equipment/equipment.component';
import { ActivityComponent } from './activity/activity.component';
import { ParcComponent } from './parc/parc.component';
import { StaticsComponent } from './statics/statics.component';

import { FormsModule } from '@angular/forms';

import { ReactiveFormsModule } from '@angular/forms';

import { DocsComponentsModule } from '@docs-components/docs-components.module';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';

import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';






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
  SpinnerModule,
  TableModule,
  TabsModule,
  TooltipModule,
  UtilitiesModule,
  ButtonGroupModule,
  NavbarModule,
  ModalModule,
  AlertModule,
  ProgressModule,
  SharedModule,
  WidgetModule


} from '@coreui/angular';
import { IconModule } from '@coreui/icons-angular';
import { ChartjsModule } from '@coreui/angular-chartjs';





@NgModule({
  declarations: [
    EquipmentComponent,
    ActivityComponent,
    ParcComponent,
    StaticsComponent
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
    NgxPaginationModule,
    ModalModule,
    NgxDatatableModule,
    AlertModule,
    WidgetModule,
    ChartjsModule

  ]
})
export class ParcModule { }
