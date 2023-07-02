
import { Router } from '@angular/router';
import { Product } from './../../../../model/Product';
import { Component, OnInit } from '@angular/core';
import { ProductService } from './../../../services/product.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  currentPage: any = 1;
  itemsPerPage: any = 10;
  prod: Product = new Product();
  addFormVisible: boolean = false;
  editFormVisible: boolean = false;
  prods!: Product[];
  term:string="all"
  totalproducts!: number;
  total !: any
  constructor(
    private prodservice: ProductService,
    private router: Router,


  ) { }

  ngOnInit(): void {
    this.getproducts()
  }


  createproduct() {
    console.log(this.prod)
    this.hideEditForm()

    this.prodservice.createProduct(this.prod).subscribe(data => {
      console.log(data)
      Swal.fire({
        icon: 'info',
        text: 'product ajouter avec succès!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      this.getproducts()
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
    this.prod = new Product();


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


  deleteproduct(product: Product) {
    this.prodservice.deleteProduct(product.idProduct).subscribe(data => {


      Swal.fire({
        icon: 'info',

        text: 'product supprimée!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
      this.getproducts()
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

  editproduct(product: Product) {
    this.hideAddForm()
    this.prodservice.findProductById(product.idProduct).subscribe(data => {
      this.prod = data;
      this.showEditForm()
    }
    );

  }
  updateproduct(product: Product) {

    this.prodservice.updateProduct(product, product.idProduct).subscribe(data => {
      this.prod = data;
      Swal.fire({
        icon: 'info',

        text: 'product Modifier avec succès!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      this.getproducts()
      this.prod = new Product();
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
    this.router.navigate(['/products'])
  }




}