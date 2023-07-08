import { Component } from '@angular/core';
import { ReservationService } from '../../../services/reservation.service';
import { ngbPositioning } from '@ng-bootstrap/ng-bootstrap/util/positioning';


@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent {



  nbreservation: number;
  nbreservationParc : number;
  nbreservationActivity: number;
  chartActivity : number;
 parc: number;
 activity : number;

  
  

  constructor(private reservationService: ReservationService ){}

  ngOnInit() {
    this.getNbResservation();
    this.getNbResservationParc();
    this.getNbResservationActivity();

  }

  getNbResservation() {
    this.reservationService.getNbReservation().subscribe(
      nbr => {
        this.nbreservation = nbr;
        //this.calculateEnabledParcPercentage();

      },
      error => {
        console.error('Failed to get parc count:', error);
      }
    );
  }


  getNbResservationParc(): number {
    this.reservationService.getNbReservationParc().subscribe(
      nbr => {
        this.nbreservationParc = nbr;
        //this.calculateEnabledParcPercentage();


      }
    );
    return this.nbreservationParc;
  }

   getNbResservationActivity(): number {
    this.reservationService.getNbReservationActivity().subscribe(
      nbr => {
        this.nbreservationActivity = nbr;
        //this.calculateEnabledParcPercentage();
      }
    );
    return this.nbreservationActivity;
  }
  


  

  data = {
    labels: ['Activity', 'Parc'],
    datasets: [{
      backgroundColor: ['#41B883', '#E46651'],
      //data: [this.getNbResservationParc()*10, this.getNbResservationActivity()*10]
      data: [20,80]
    }]
  };

}
