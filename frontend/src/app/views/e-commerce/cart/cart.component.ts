import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/model/Cart';
import { CartService } from './../../../services/Cart.service';
import { Router } from '@angular/router';
import Swal, { SweetAlertCustomClass } from 'sweetalert2';
import { CartItem } from 'src/model/CartItem';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  currentPage: any = 1;
  itemsPerPage: any = 10;
  cart: Cart = new Cart();
  addFormVisible: boolean = false;
  editFormVisible: boolean = false;
  carts!: Cart[];

  totalCarts!: number;
  total !: any
  constructor(
    private cartservice: CartService,
    private router: Router,


  ) { }

  ngOnInit(): void {
    this.getCarts()
  }


  createCart() {
    console.log(this.cart)
    this.hideEditForm()

    this.cartservice.createCart(this.cart).subscribe(data => {
      console.log(data)
      Swal.fire({
        icon: 'info',
        text: 'Cart ajouter avec succès!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      this.getCarts()
      this.hideAddForm()
    }, error => {
      console.log(error);
      Swal.fire({
        icon: 'error',
        text: 'Error, server not responding!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
    });
    this.cart = new Cart();


  }

  getCarts() {

    this.cartservice.getCarts().subscribe(data => {
      if (data != null) {
        console.log(data.length)
        this.carts = data;
        this.totalCarts = data.length;
        this.total = this.totalCarts / 10
      } else {
        this.totalCarts = 0;
        this.carts = [];
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


  deleteCart(Cart: Cart) {
    this.cartservice.deleteCart(Cart.idCart).subscribe(data => {


      Swal.fire({
        icon: 'info',

        text: 'Cart supprimée!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
      this.getCarts()
      this.hideAddForm()

    }, error => {
      Swal.fire({
        icon: 'error',

        text: 'Error, server not responding!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
      console.log(error)
    })

  }

  editCart(Cart: Cart) {
    this.hideAddForm()
    this.cartservice.findCartById(Cart.idCart).subscribe(data => {
      this.cart = data;
      this.showEditForm()
    }
    );

  }
  info(c: Cart) {
    if (c.cartItems.length > 0) {
      const tableHeaders = ['Product', 'Price', 'Quantity'];
      const tableRows = c.cartItems.map((cartItem: CartItem) => {
        return [cartItem.product.name, cartItem.price, cartItem.quantity];
      });

      Swal.fire({
        icon: 'info',
        title: 'Cart Information',
        html: this.generateTableHTML(tableHeaders, tableRows),
      })
    } else {
      Swal.fire({
        icon: 'info',
        text: 'Cart Empty!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
    }
  }
  generateTableHTML(headers: string[], rows: any[]) {
    const tableHeaderHTML = `<tr>${headers.map(header => `<th>${header}</th>`).join('')}</tr>`;
    const tableRowsHTML = rows.map(row => `<tr>${row.map((item: any) => `<td>${item}</td>`).join('')}</tr>`).join('');

    return `<table style="width: 100%;
  border-collapse: collapse;">${tableHeaderHTML}${tableRowsHTML}</table>`;
  }
  updateCart(Cart: Cart) {

    this.cartservice.updateCart(Cart, Cart.idCart).subscribe(data => {
      this.cart = data;
      Swal.fire({
        icon: 'info',

        text: 'Cart Modifier avec succès!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      this.getCarts()

      this.hideEditForm()
    }, error => {
      Swal.fire({
        icon: 'error',
        text: 'Error, server not responding!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      console.log(error)
    });
  }
  showAddForm() {
    this.addFormVisible = true;

  }
  hideAddForm() {
    this.addFormVisible = false;
  }

  showEditForm() {
    this.editFormVisible = true;
  }
  hideEditForm() {
    this.editFormVisible = false;
  }



  gotoTop() {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }
  redirectToList() {
    this.router.navigate(['/Carts'])
  }




}