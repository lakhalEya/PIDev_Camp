import { Component, OnInit } from '@angular/core';
import { CityService } from './../services/city.service'; // Import your ParcService
import { City } from './../models/city.model'; // Import your ParcService
import {  ParcService } from './../services/parc.service'; // Import your ParcService
import { Parc } from './../models/parc.model';
import { PaginationInstance } from 'ngx-pagination';

import { getStyle } from '@coreui/utils';
import { ChartjsComponent } from '@coreui/angular-chartjs';
import { StaticsComponent } from './../statics/statics.component'; // Replace './statics.component' with the correct path to the StaticsComponent file
import { StaticsService } from './../services/statics.service';

@Component({
  selector: 'app-parc',
  templateUrl: './parc.component.html',
  styleUrls: ['./parc.component.scss']
})
export class ParcComponent  implements OnInit {

  constructor(private cityService: CityService, private parcService: ParcService) { }

  page: number = 1;
  count: number = 0;
  tableSize: number = 7;
  tableSizes: any = [3, 6, 9, 12];
  parcs: Parc[];
  cities: City[];
  isAllSelected = false;
  selectedParc: Parc | null = null;
  deleteModal: any;
  showSuccessAlert : boolean = false;
  showErrorAlert : boolean = false;
  errorMessage: string;
  message: string;

  public panes = [
    { name: 'Home 01', id: 'tab-01' },
    { name: 'Profile 02', id: 'tab-02' },
    { name: 'Contact 03', id: 'tab-03' }
  ];


  ngOnInit() {
    this.getAllParcs();
    this.getAllCities();

  }


  toggleSelectAll() {
    // Toggle the select status for all rows
    this.isAllSelected = !this.isAllSelected;

    // Set the 'selected' property for all Parc objects
    this.parcs.forEach((parc) => (parc.selected = this.isAllSelected));
  }

  getAllParcs() {
    this.parcService.getAllParcs().subscribe(
      (data:Parc[]) => this.parcs = data) };

  getAllCities() {
    this.cityService.getAllCity().subscribe(
      (data:City[]) => this.cities = data) };

      onTableDataChange(event: any) {
        this.page = event;
        this.getAllParcs();
      }

      onTableSizeChange(event: any): void {
        this.tableSize = event.target.value;
        this.page = 1;
        this.getAllParcs();
      }

      deleteParc() {
        console.log(this.selectedParc);
        console.log("test deleteParc");

        if (this.selectedParc) {
          const parcId = this.selectedParc.idParc;

          this.parcService.deleteParc(parcId).subscribe(
            response => {
              console.log('Response code:', response.code);
              console.log('Response message:', response.message);
              if(response.code == 200)
              {
                this.parcs = this.parcs.filter((parc) => parc.idParc !== parcId);
                this.selectedParc = null;
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



