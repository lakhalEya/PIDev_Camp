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

  

  onSubmit1() {
    this.customStylesValidated = true;
    console.log('Submit... 1');
  }

  onReset1() {
    this.customStylesValidated = false;
    console.log('Reset... 1');
  }

  onSubmit2() {
    this.browserDefaultsValidated = true;
    console.log('Submit... 2');
  }

  onReset2() {
    this.browserDefaultsValidated = false;
    console.log('Reset... 3');
  }

  onSubmit3() {
    this.tooltipValidated = true;
    console.log('Submit... 3');
  }

  onReset3() {
    this.tooltipValidated = false;
    console.log('Reset... 3');
  }
  save(){
    this.reservationService.addForParc(this.reservation).subscribe(data => this.router.navigateByUrl('/reservation'));
   
  }



  

}
