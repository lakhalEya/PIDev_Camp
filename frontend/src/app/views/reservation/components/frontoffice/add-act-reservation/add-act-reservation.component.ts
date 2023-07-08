import { Component } from '@angular/core';
import { Reservation } from '../../../models/reservation';
import { ReservationService } from '../../../services/reservation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-act-reservation',
  templateUrl: './add-act-reservation.component.html',
  styleUrls: ['./add-act-reservation.component.scss']
})
export class AddActReservationComponent {
  customStylesValidated = false;
  browserDefaultsValidated = false;
  tooltipValidated = false;


  reservation: Reservation;

  constructor(private  reservationService:ReservationService,private router: Router) { }


  ngOnInit(): void { 

    this.reservation = new Reservation();


  }

  save1(){
    this.reservationService.addForActivity(this.reservation).subscribe(data => this.router.navigateByUrl('/pricing-promotion'));
   
  }


}
