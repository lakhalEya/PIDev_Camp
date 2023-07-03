import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';
import { Product } from 'src/model/Product';
import Swal from 'sweetalert2';
import { CartService } from '../services/Cart.service';
import { CartItem } from 'src/model/CartItem';
import { CartItemservice } from '../services/Cartitem.service';
import { Cart } from 'src/model/Cart';
import { Orderservice } from '../services/Order.service';
import { Order } from 'src/model/Order';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.scss']
})
export class PanierComponent implements OnInit {
  currentPage: any = 1;
  itemsPerPage: any = 10;
  prods!: CartItem[];
  totalproducts!: number;
  cart!: Cart
  total!: string
  idcart: number = 4  // remplacer from user
  constructor(
    private cartservice: CartService,
    private router: Router,
    private cartitemservice: CartItemservice,
    private os: Orderservice
  ) { }
  ngOnInit(): void {
    this.getproducts()
    this.getcart();
  }


  private getcart() {
    this.cartservice.findCartById(this.idcart).subscribe(d => {
      this.cart = d;
    });
  }

  decrementValue(prod: CartItem,price:number ) {
    if (prod.quantity > 1) {
      prod.quantity = prod.quantity - 1;
      prod.price = price * prod.quantity;
      this.newMethod(this.prods);
    }
 
  }
  incrementValue(prod: CartItem,price:number) {
    prod.quantity = prod.quantity + 1
    prod.price = price * prod.quantity
    this.newMethod(this.prods);
  }
  remove(prod: CartItem) {
    this.cartitemservice.deleteitem(prod.id).subscribe(d => {
      Swal.fire({
        icon: 'info',
        text: 'Item removed !',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
      this.getproducts()

    })
  }
  order() {
    let order = new Order()
    order.cart = this.cart
    // Get current time
    const currentTime = new Date();

    // Format the time as HH:mm
    const options: Intl.DateTimeFormatOptions = { hour: '2-digit', minute: '2-digit', hour12: false };
    const formattedTime = currentTime.toLocaleTimeString([], options);

    console.log(formattedTime); // Output: 04:08

    order.ordered = formattedTime.toString()
    order.shipped = new Date()
    order.total = this.total;
    order.status = "en cours"

    this.os.createOrder(order, this.cart.idCart).subscribe(d => {
      Swal.fire({
        icon: 'info',
        text: 'Succes shopping',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
    })


  }
  getproducts() {

    this.cartservice.findCartById(this.idcart).subscribe(data => {
      if (data != null) {

        this.prods = data.cartItems;
        this.totalproducts = data.cartItems.length;
     
       this.newMethod(data.cartItems);
      } else {
        this.totalproducts = 0;
        this.prods = [];
      }
    }, error => {
      Swal.fire({
        icon: 'error',
        text: 'Error, server not responding!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
    });
  }

  private newMethod(cartItems:  CartItem[]) {
    let tot = 0
    cartItems.forEach(Element => {
      console.log(Element.price);
      tot = tot + Element.price;
    });
    const nombreFormatte: string = tot.toFixed(3);
    this.total = nombreFormatte;
    return tot;
  }
}
