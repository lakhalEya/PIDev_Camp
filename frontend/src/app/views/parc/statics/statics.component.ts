import { Component, OnInit  } from '@angular/core';
import { StaticsService } from './../services/statics.service';


@Component({
  selector: 'app-statics',
  templateUrl: './statics.component.html',
  styleUrls: ['./statics.component.scss']
})
export class StaticsComponent
{
  enabledParcCount: number;
  parcCount: number;
  mostUsedCategory: string;

  constructor(private staticsService: StaticsService) { }

  ngOnInit() {
    this.getEnabledParcCount();
    this.getParcCount();
    this.getMostUsedCategory();
  }

  getEnabledParcCount() {
    this.staticsService.getEnabledParcCount().subscribe(
      count => {
        this.enabledParcCount = count;
      },
      error => {
        console.error('Failed to get enabled parc count:', error);
      }
    );
  }

  getParcCount() {
    this.staticsService.getParcCount().subscribe(
      count => {
        this.parcCount = count;
      },
      error => {
        console.error('Failed to get parc count:', error);
      }
    );
  }

  getMostUsedCategory() {
    this.staticsService.getMostUsedCategory().subscribe(
      category => {
        this.mostUsedCategory = category;
      },
      error => {
        console.error('Failed to get most used category:', error);
      }
    );
  }
}
