import { Component,OnInit } from '@angular/core';
import { Sale } from 'src/app/views/reservation/models/sale'
import { SaleService } from 'src/app/views/reservation/services/sale.service';

@Component({
  selector: 'app-list-sales',
  templateUrl: './list-sales.component.html',
  styleUrls: ['./list-sales.component.scss']
})
export class ListSalesComponent  implements OnInit {
 
  sales : Sale[] = [];
  deleteModal: any;
  showSuccessAlert : boolean = false;
  showErrorAlert : boolean = false;
  errorMessage: string;
  message: string;
  selectedSale: Sale| null = null;


  colors = [
    { color: 'primary', textColor: 'primary' },
    //{ color: 'secondary', textColor: 'secondary' },
    //{ color: 'success', textColor: 'success' },
    //{ color: 'danger', textColor: 'danger' },
    //{ color: 'warning', textColor: 'warning' },
    //{ color: 'info', textColor: 'info' },
    //{ color: 'light' },
    //{ color: 'dark' }
  ];
  constructor(private  saleService:SaleService) { }

  ngOnInit():void {

    this.saleService.getAll().subscribe(
      data=> this.sales = data
    )

   }


   deleteSale() {
    console.log(this.selectedSale);
    if (this.selectedSale) {
      const saleId = this.selectedSale.id;

      this.saleService.deleteSale(saleId).subscribe(
        response => {
          console.log('Response code:', response.code);
          console.log('Response message:', response.message);
          if(response.code == 200)
          {
            this.sales = this.sales.filter((sale) => sale.id !== saleId);
            this.selectedSale = null;
            this.showSuccessAlert = true;
            this.message= response.message;
            setTimeout(() => {
              this.showSuccessAlert = false;
            }, 3000);

          }
          else
          {
            this.showErrorAlert = true;
            this.errorMessage= response.message;
            setTimeout(() => {
              this.showErrorAlert = false;
            }, 3000);
          }
          // Handle the response code and message accordingly
        },
        error => {
          console.error('An error occurred:', error);
          // Handle the error
        }
      );
    }
  }
}
