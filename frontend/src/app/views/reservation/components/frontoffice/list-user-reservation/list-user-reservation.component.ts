import { Component } from '@angular/core';
import { Reservation } from '../../../models/reservation';
import { ReservationService } from '../../../services/reservation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-user-reservation',
  templateUrl: './list-user-reservation.component.html',
  styleUrls: ['./list-user-reservation.component.scss']
})
export class ListUserReservationComponent {


  reservations : Reservation[] = [];
  cancelModal: any;
  showSuccessAlert : boolean = false;
  showErrorAlert : boolean = false;
  errorMessage: string;
  message: string;
  selectedReservation: Reservation| null = null;
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

  constructor(private  reservationService:ReservationService ,private router: Router) { }

  ngOnInit():void {

    this.reservationService.getAll().subscribe(
      data=> this.reservations = data
    )

   }

   goToAddReservation() {
    this.router.navigateByUrl('http://localhost:4200/#/dashboard/add-reservation');
  }


 cancelReservation() {
    console.log(this.selectedReservation);
    if (this.selectedReservation) {
      const resId = this.selectedReservation.id;

      this.reservationService.cancelReservation(resId).subscribe(
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
        },
        error => {
          console.error('An error occurred:', error);
        }
      );
    }
  }

}
