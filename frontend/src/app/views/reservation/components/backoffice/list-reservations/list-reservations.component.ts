import { Component, OnInit } from '@angular/core';
import { Reservation } from 'src/app/views/reservation/models/reservation'
import { ReservationService } from 'src/app/views/reservation/services/reservation.service';
import { Sale } from '../../../models/sale';
import { SaleService } from '../../../services/sale.service';

@Component({
  selector: 'app-list-reservations',
  templateUrl: './list-reservations.component.html',
  styleUrls: ['./list-reservations.component.scss']
})
export class ListReservationsComponent implements OnInit {

  reservations : Reservation[] = [];
  deleteModal: any;
  showSuccessAlert : boolean = false;
  showErrorAlert : boolean = false;
  errorMessage: string;
  message: string;
  selectedReservation: Reservation | null = null;
  sales: Sale[];
  selectedSale: Sale | null = null; // Updated type to Sale and initialized as null


  

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

  constructor(private  reservationService:ReservationService,private saleService: SaleService) { }

  ngOnInit():void {

    this.reservationService.getAll().subscribe(
      data=> this.reservations = data
    )
    this.saleService.getAll()
      .subscribe(sales => this.sales = sales);

   }

   getSales(): void {
    this.saleService.getAll()
      .subscribe(sales => this.sales = sales);
  }


  assignSaleToReservation(sale : Sale): void {
    if (this.selectedReservation && this.selectedSale){
      this.selectedReservation.sale = this.selectedSale;
   
      this.reservationService.assignSale(this.selectedReservation,this.selectedSale)
        .subscribe(
          assignSale => {
            // Handle the successful update
            console.log('Reservation updated:', assignSale);
            // Perform any additional actions or show success messages
          },
          error => {
            // Handle errors
            console.error('Error updating reservation:', error);
            // Perform error handling or show error messages
          }
        );
    }
  
  }

   deleteReservation() {
    console.log(this.selectedReservation);
    if (this.selectedReservation) {
      const resId = this.selectedReservation.id;

      this.reservationService.deleteReservation(resId).subscribe(
        response => {
          console.log('Response code:', response.code);
          console.log('Response message:', response.message);
          if(response.code == 200)
          {
            this.reservations = this.reservations.filter((res) => res.id !== resId);
            this.selectedReservation = null;
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
