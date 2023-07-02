import { Component, OnInit } from '@angular/core';
import { Product } from 'src/model/Product';
import { ProductService } from '../services/product.service';
import { CartItemservice } from '../services/Cartitem.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { CartItem } from 'src/model/CartItem';
import { CartService } from '../services/Cart.service';
import { AllocationService } from '../services/Allocation.service';

import { Cart } from 'src/model/Cart';
import { Allocationitem } from 'src/model/Allocationitem';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  idcart: number = 4
  idallocation: number = 7
  /********** replacer   par cartid of user   and allocation id of user ************/

  /**********************/
  currentPage: any = 1;
  itemsPerPage: any = 10;
  prod: Product = new Product();
  item!: CartItem
  prods!: Product[];
  ai!: Allocationitem;
  totalproducts!: number;
  total !: any
  term:string="all"
  cart!: Cart
  totitem!: number
  constructor(
    private prodservice: ProductService,
    private router: Router,
    private itemservice: CartItemservice,
    private cartservice: CartService,
    private alocservice: AllocationService
  ) { }
  getcart() {
    this.cartservice.findCartById(this.idcart).subscribe(data => {
      this.cart = data;
      this.totitem = this.cart.cartItems.length
    })
  }
  ngOnInit(): void {
    this.getproducts()
    this.getcart()
  }

  tocart(product: Product) {
    this.item = new CartItem()
    this.item.price = product.price;
    this.item.quantity = 1
    this.item.product = product;
    console.log(this.item)
    this.getcart()
    this.itemservice.createitem(this.item, this.idcart).subscribe(d => {
      Swal.fire({
        icon: 'info',
        text: 'Added To carte ',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
    })

  }
  navigate(){
    this.router.navigate(['/panier'])
  }
  command(product: Product) {
    this.alocservice.findAllocationById(this.idallocation).subscribe(d => {
      console.log(d)
      this.ai = new Allocationitem()
      this.ai.Quantity = 1
      this.ai.price = product.price
      this.ai.product = product
      this.ai.dateDebut = new Date()
      this.ai.product=product
      console.log(this.ai)
 
    
       this.alocservice.updateAllocation(d).subscribe(data=>{
          Swal.fire({
            icon: 'info',
            text: 'Reservation avec succéss ',
            showConfirmButton: false,
            timer: 2000, // 2 seconds
    
          });
        })
    })

  }

  getproducts() {

    this.prodservice.getProducts().subscribe(data => {
      if (data != null) {
        console.log(data.length)
        this.prods = data;
        this.totalproducts = data.length;
        this.total = this.totalproducts / 10
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




  editproduct(product: Product) {

    this.prodservice.findProductById(product.idProduct).subscribe(data => {
      this.prod = data;

    }
    );

  }





}
