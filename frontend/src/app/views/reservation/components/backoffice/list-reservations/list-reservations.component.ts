import { Component, OnInit } from '@angular/core';
import { Reservation } from 'src/app/views/reservation/models/reservation'
import { ReservationService } from 'src/app/views/reservation/services/reservation.service';

@Component({
  selector: 'app-list-reservations',
  templateUrl: './list-reservations.component.html',
  styleUrls: ['./list-reservations.component.scss']
})
export class ListReservationsComponent implements OnInit {

  reservations : Reservation[] = [];

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

  constructor(private  reservationService:ReservationService) { }

  ngOnInit():void {

    this.reservationService.getAll().subscribe(
      data=> this.reservations = data
    )

   }


}
