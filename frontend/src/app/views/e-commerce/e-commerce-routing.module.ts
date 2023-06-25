import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { OrdersComponent } from './orders/orders.component';
import { CartComponent } from './cart/cart.component';


const routes: Routes = [
  {
    path: '',
    data: {
      title: 'e-commerce',
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'products',
      },
      {
        path: 'products',
        component: ProductsComponent,
        data: {
          title: 'products',
        },
      },
      {
        path: 'orders',
        component: OrdersComponent,
        data: {
          title: 'orders',
        },
      },
      {
        path: 'carts',
        component: CartComponent,
        data: {
          title: 'carts',
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ECommerceRoutingModule {}

