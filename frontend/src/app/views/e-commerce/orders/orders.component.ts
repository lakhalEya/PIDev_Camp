import { Component, OnInit } from '@angular/core';
import { Order } from 'src/model/Order';
import { Orderservice } from './../../../services/Order.service';
import { Router } from '@angular/router';
import Swal, { SweetAlertCustomClass } from 'sweetalert2';
import { CartItem } from 'src/model/CartItem';
import { CartService } from 'src/app/services/Cart.service';
import { Cart } from 'src/model/Cart';
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  currentPage: any = 1;
  itemsPerPage: any = 10;
  Order: Order = new Order();
  addFormVisible: boolean = false;
  editFormVisible: boolean = false;
  Orders!: Order[];
  carts!: Cart[];
  totalOrders!: number;
  total !: any
  idcart!:number
  constructor(
    private orderservice: Orderservice,
    private router: Router,
    private cartservice: CartService,

  ) { }

  ngOnInit(): void {
    this.getOrders()
    this.getallcart();
  }

 getallcart()
 {
  this.cartservice.getCarts().subscribe(d=>{
    this.carts=d;
  })
 }
 state(event:any){
   this.idcart=event.target.value;
 }
  createOrder() {
    console.log(this.Order)
    this.hideEditForm()

    this.orderservice.createOrder(this.Order,  this.idcart).subscribe(data => {
      console.log(data)
      Swal.fire({
        icon: 'info',
        text: 'Order ajouter avec succès!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      this.getOrders()
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
    this.Order = new Order();


  }

  getOrders() {

    this.orderservice.getOrders().subscribe(data => {
      if (data != null) {
        console.log(data.length)
        this.Orders = data;
        this.totalOrders = data.length;
        this.total = this.totalOrders / 10
      } else {
        this.totalOrders = 0;
        this.Orders = [];
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


  deleteOrder(Order: Order) {
    this.orderservice.deleteOrder(Order.id).subscribe(data => {


      Swal.fire({
        icon: 'info',

        text: 'Order supprimée!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
      this.getOrders()
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

  editOrder(Order: Order) {
    this.hideAddForm()
    this.orderservice.findOrderById(Order.id).subscribe(data => {
      this.Order = data;
      this.showEditForm()
    }
    );

  }
  info(c: Order) {
    if (c.cart) {
   

      Swal.fire({
        icon: 'info',
        title: 'Order Information',
        html: ""
      })
    } else {
      Swal.fire({
        icon: 'info',
        text: 'Order Empty!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
    }
  }
 
  updateOrder(Order: Order) {

    this.orderservice.updateOrder(Order, Order.id).subscribe(data => {
      this.Order = data;
      Swal.fire({
        icon: 'info',

        text: 'Order Modifier avec succès!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      this.getOrders()

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
    this.router.navigate(['/Orders'])
  }




}