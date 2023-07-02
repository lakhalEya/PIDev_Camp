import { Allocation } from 'src/model/Allocation';
import { Component, OnInit } from '@angular/core';
import { AllocationitemService } from './../../../services/Allocationitem.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Allocationitem } from 'src/model/Allocationitem';
import { Product } from 'src/model/Product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {

  currentPage: any = 1;
  itemsPerPage: any = 10;
  allocation: Allocationitem = new Allocationitem();
  addFormVisible: boolean = false;
  editFormVisible: boolean = false;
  allocations!: Allocationitem[];
  idallocation: number = 7;
  totalallocations!: number;
  total !: any
  prods!: Product[];
  idprod!:number
  constructor(
    private allocationservice: AllocationitemService,
    private router: Router,
    private prodservice: ProductService,

  ) { }

  ngOnInit(): void {
    this.getallocations()
    this.getprods()
  }
  state(event:any){
    this.idprod=event.target.value
   // console.log(this.allocation)
    this.prodservice.findProductById(this.idprod).subscribe(d=>{
       this.allocation.product=d;
      
     })
 
  }
  getprods() {
    this.prodservice.getProducts().subscribe(d => {
      this.prods = d;
    })
  }
  createallocation() {
  
   
   this.hideEditForm()

    this.allocationservice.createlocitem(this.allocation, this.idallocation).subscribe(data => {
      console.log(data)
      Swal.fire({
        icon: 'info',
        text: 'allocation ajouter avec succès!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      this.getallocations()
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
  // this.allocation = new Allocationitem();


  }

  getallocations() {

    this.allocationservice.getlocitems().subscribe(data => {
      if (data != null) {
        console.log(data.length)
        this.allocations = data;
        this.totalallocations = data.length;
        this.total = this.totalallocations / 10
      } else {
        this.totalallocations = 0;
        this.allocations = [];
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


  deleteallocation(allocation: Allocationitem) {
    this.allocationservice.deletelocitem(allocation.id).subscribe(data => {


      Swal.fire({
        icon: 'info',

        text: 'allocation supprimée!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });
      this.getallocations()
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

  editallocation(allocation: Allocationitem) {
    this.hideAddForm()
    this.allocationservice.findlocitemById(allocation.id).subscribe(data => {

    this.allocation = data;
    this.showEditForm()
    }
    );

  }

  generateTableHTML(headers: string[], rows: any[]) {
    const tableHeaderHTML = `<tr>${headers.map(header => `<th>${header}</th>`).join('')}</tr>`;
    const tableRowsHTML = rows.map(row => `<tr>${row.map((item: any) => `<td>${item}</td>`).join('')}</tr>`).join('');

    return `<table style="width: 100%;
  border-collapse: collapse;">${tableHeaderHTML}${tableRowsHTML}</table>`;
  }
  updateallocation(allocation: Allocationitem) {

    this.allocationservice.updatelocitem(this.allocation, allocation.id).subscribe(data => {
      this.allocation = data;
      Swal.fire({
        icon: 'info',

        text: 'allocation Modifier avec succès!',
        showConfirmButton: false,
        timer: 2000, // 2 seconds

      });

      this.getallocations()

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
    this.router.navigate(['/resevation'])
  }




}