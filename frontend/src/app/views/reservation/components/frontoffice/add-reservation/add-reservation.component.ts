import { Component, OnInit } from '@angular/core';
import { Reservation } from '../../../models/reservation';
import { ReservationService } from '../../../services/reservation.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.scss']
})
export class AddReservationComponent implements OnInit {
  customStylesValidated = false;
  browserDefaultsValidated = false;
  tooltipValidated = false;


  reservation: Reservation;

  constructor(private  reservationService:ReservationService,private router: Router) { }

  ngOnInit(): void { 

    this.reservation = new Reservation();


  }

  save(){
    this.reservationService.addForParc(this.reservation).subscribe(data => this.router.navigateByUrl('/pricing-promotion'));
   
  }
  save1(){
    this.reservationService.addForActivity(this.reservation).subscribe(data => this.router.navigateByUrl('/pricing-promotion'));
   
  }



  

}
