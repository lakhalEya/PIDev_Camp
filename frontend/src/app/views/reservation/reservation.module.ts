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
  ButtonGroupModule
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





@NgModule({
  declarations: [
    PromotionComponent,
    ReservationComponent,
    ListReservationsComponent,
    ListSalesComponent,
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
    FormsModule,
    ReactiveFormsModule,
    DropdownModule,
    PaginationModule,
    PopoverModule,
    TableModule,
    DocsComponentsModule,
    DropdownModule,
    SharedModule,
    NgbDatepickerModule
  ]
})
export class ReservationModule { }
