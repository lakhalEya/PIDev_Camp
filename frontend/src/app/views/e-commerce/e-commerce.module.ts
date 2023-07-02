import { FilterPipe } from './../../pipes/FilterPipe.pipe';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ECommerceRoutingModule } from './e-commerce-routing.module';
import { ProductsComponent } from './products/products.component';
import { OrdersComponent } from './orders/orders.component';
import { CartComponent } from './cart/cart.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    FilterPipe,
    ProductsComponent,
    OrdersComponent,
    CartComponent,
  ],
  imports: [
    CommonModule,FormsModule,
    ECommerceRoutingModule
  ]
})
export class ECommerceModule { }
