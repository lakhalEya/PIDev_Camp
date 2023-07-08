import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservationRoutingModule } from './reservation-routing.module';
import { PromotionComponent } from './promotion/promotion.component';
import { ReservationComponent } from './reservation/reservation.component';
import { ListReservationsComponent } from './components/backoffice/list-reservations/list-reservations.component';


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
  ProgressComponent,
  ProgressModule,
  SharedModule,
  SpinnerModule,
  TableModule,
  TabsModule,
  TooltipModule,
  UtilitiesModule,
  ButtonGroupModule,
  ModalModule,
  AlertModule,
  WidgetModule
} from '@coreui/angular';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IconModule } from '@coreui/icons-angular';
// utils
import { DocsComponentsModule } from '@docs-components/docs-components.module';
import { AccordionsComponent } from '../base/accordion/accordions.component';
import { BreadcrumbsComponent } from '../base/breadcrumbs/breadcrumbs.component';
import { CardsComponent } from '../base/cards/cards.component';
import { CarouselsComponent } from '../base/carousels/carousels.component';
import { CollapsesComponent } from '../base/collapses/collapses.component';
import { NavsComponent } from '../base/navs/navs.component';
import { ListGroupsComponent } from '../base/list-groups/list-groups.component';
import { PaginationsComponent } from '../base/paginations/paginations.component';
import { PopoversComponent } from '../base/popovers/popovers.component';
import { SpinnersComponent } from '../base/spinners/spinners.component';
import { TablesComponent } from '../base/tables/tables.component';
import { TooltipsComponent } from '../base/tooltips/tooltips.component';
import { TabsComponent } from '../base/tabs/tabs.component';
import { PlaceholdersComponent } from '../base/placeholders/placeholders.component';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { ListSalesComponent } from './components/backoffice/list-sales/list-sales.component';
import { AddSaleComponent } from './components/backoffice/add-sale/add-sale.component';
import { ListUserReservationComponent } from './components/frontoffice/list-user-reservation/list-user-reservation.component';
import { AddReservationComponent } from './components/frontoffice/add-reservation/add-reservation.component';
import { StatisticsComponent } from './components/backoffice/statistics/statistics.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { NgxPaginationModule } from 'ngx-pagination';
import { ChartjsModule } from '@coreui/angular-chartjs';
import { HttpClientModule } from '@angular/common/http';
import { AddActReservationComponent } from './components/frontoffice/add-act-reservation/add-act-reservation.component';



@NgModule({
  declarations: [
    PromotionComponent,
    ReservationComponent,
    ListReservationsComponent,
    ListSalesComponent,
    AddSaleComponent,
    ListUserReservationComponent,
    AddReservationComponent,
    StatisticsComponent,
    AddActReservationComponent
    
   /* AccordionsComponent,
    BreadcrumbsComponent,
    CardsComponent,
    CarouselsComponent,
    CollapsesComponent,
    ListGroupsComponent,
    NavsComponent,
    PaginationsComponent,
    PopoversComponent,
    //ProgressComponent,
    SpinnersComponent,
    TablesComponent,
    TooltipsComponent,
    TabsComponent,
    PlaceholdersComponent,*/
    
  ],
  imports: [
    CommonModule,
    ReservationRoutingModule,
    CommonModule,
    AccordionModule,
    BadgeModule,
    BreadcrumbModule,
    ButtonModule,
    CardModule,
    CollapseModule,
    GridModule,
    UtilitiesModule,
    SharedModule,
    ListGroupModule,
    IconModule,
    ListGroupModule,
    PlaceholderModule,
    ProgressModule,
    SpinnerModule,
    TabsModule,
    NavModule,
    TooltipModule,
    CarouselModule,
    FormModule,
    ReactiveFormsModule,
    DropdownModule,
    PaginationModule,
    PopoverModule,
    TableModule,
    DocsComponentsModule,
    DropdownModule,
    SharedModule,
    NgbDatepickerModule,
    FormsModule ,
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
    ButtonGroupModule,
    IconModule,
    ReactiveFormsModule,
    DocsComponentsModule,
    NgxPaginationModule,
    ModalModule,
    NgxDatatableModule,
    AlertModule,
    WidgetModule,
    ChartjsModule
    
  ]
})
export class ReservationModule { }
