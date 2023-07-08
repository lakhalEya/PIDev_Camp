import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ForumComponent } from './forum/forum.component';
import { SocialRoutingModule } from './social-routing.module';
import { PostComponent } from './post/post.component';
import { ReactionComponent } from './reaction/reaction.component';




import { FormsModule } from '@angular/forms';

import { ReactiveFormsModule } from '@angular/forms';

import { DocsComponentsModule } from '@docs-components/docs-components.module';
import { HttpClientModule } from '@angular/common/http';


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
import { ListPostComponent } from './list-post/list-post.component';
import { ListReactionsComponent } from './list-reactions/list-reactions.component';

@NgModule({
  declarations: [
    ForumComponent,
    PostComponent,
    ReactionComponent,
    ListPostComponent,
    ListReactionsComponent,
  ],
  imports: [
    CommonModule,
    SocialRoutingModule,
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
    NavbarModule,
    ModalModule,
    AlertModule,
    WidgetModule,
    ChartjsModule
  ]
})
export class SocialModule { }
