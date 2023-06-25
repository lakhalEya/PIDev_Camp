import { Component, OnInit } from '@angular/core'; 
import { Reservation } from 'src/app/models/booking/reservation';
import { ReservationService } from 'src/app/services/booking/reservation.service';

@Component({
  selector: 'app-list-reservation',
  templateUrl: './list-reservation.component.html',
  styleUrls: ['./list-reservation.component.css']
})
export class ListReservationComponent implements OnInit {

  reservations : Reservation[] = [];

  constructor(private  reservationService:ReservationService) { }

  ngOnInit():void {

    this.reservationService.getAll().subscribe(
      data=> this.reservations = data
    )

   }

}
