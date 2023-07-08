import { Component, OnInit } from '@angular/core';
import { CityService } from './../services/city.service'; // Import your ParcService
import { City } from './../models/city.model'; // Import your ParcService
import {  ParcService } from './../services/parc.service'; // Import your ParcService
import { Parc } from './../models/parc.model';
import { PaginationInstance } from 'ngx-pagination';
import { Activity } from './../models/activity.model';
import {  ActivityService } from './../services/activity.service'; // Import your ParcService

import { getStyle } from '@coreui/utils';
import { ChartjsComponent } from '@coreui/angular-chartjs';



import { freeSet } from '@coreui/icons';
@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.scss']
})
export class ActivityComponent   implements OnInit {

  constructor(private cityService: CityService, private parcService: ParcService, private activityService: ActivityService) { }
  icons = freeSet ;

  page: number = 1;
  count: number = 0;
  tableSize: number = 7;
  tableSizes: any = [3, 6, 9, 12];
  parcs: Parc[];
  enabledparcs: Parc[];
  checkedParcList: Parc[]= [];
  cities: City[];
  isAllSelected = false;
  selectedParc: Parc | null = null;
  deleteModal: any;
  showSuccessAlert : boolean = false;
  showErrorAlert : boolean = false;

  errorMessage: string;
  message: string;
  minRating : number = 0;

  minCapacity : number = 0;
  maxCapacity : number = 100;
  amenitiesList: string[]= [];
  activities: Activity[] = [];

  public panes = [
    { name: 'Home 01', id: 'tab-01' },
    { name: 'Profile 02', id: 'tab-02' },
    { name: 'Contact 03', id: 'tab-03' }
  ];

  ngOnInit() {
    this.getAllActivities();
    this.findEnabledParc();
  }

  getAllActivities(): void {
    this.activityService.getAllActivities().subscribe(
      (activities: Activity[]) => {
        this.activities = activities;
      },
      (error) => {
        console.log('Failed to fetch activities:', error);
      }
    );
  }
  toggleSelectAll() {
    // Toggle the select status for all rows
    this.isAllSelected = !this.isAllSelected;
    if (this.enabledparcs) {
      this.enabledparcs.forEach((parc) => (parc.selected = this.isAllSelected));
    }
  }

  toggleParcSelection(parc: Parc) {
    if (parc.selected) {
      this.checkedParcList.push(parc);
    } else {
      const index = this.checkedParcList.findIndex(item => item.idParc === parc.idParc);
      if (index !== -1) {
        this.checkedParcList.splice(index, 1);
      }
    }
  }

  findEnabledParc() {
    this.parcService.findParcByDisponibility('ENABLED').subscribe(
      parcs => {
        this.enabledparcs = parcs;
      },
      error => {
        this.showErrorAlert = true;
        this.errorMessage = error.message;
        setTimeout(() => {
          this.showErrorAlert = false;
        }, 3000);
        console.error('Failed to find parcs by disponibility:', error);
      }
    );
  }


  onTableDataChange(event: any) {
    this.page = event;
    this.findEnabledParc();
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.findEnabledParc();
  }

  deleteParc() {
    console.log(this.selectedParc);
    console.log("test deleteParc");

  }


}
