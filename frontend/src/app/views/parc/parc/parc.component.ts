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
import { freeSet } from '@coreui/icons';


@Component({
  selector: 'app-parc',
  templateUrl: './parc.component.html',
  styleUrls: ['./parc.component.scss']
})
export class ParcComponent  implements OnInit {

  constructor(private cityService: CityService, private parcService: ParcService) { }
  icons = freeSet ;

  page: number = 1;
  count: number = 0;
  tableSize: number = 7;
  tableSizes: any = [3, 6, 9, 12];
  parcs: Parc[];
  checkedParcList: Parc[]= [];
  cities: City[];
  isAllSelected = false;
  selectedParc: Parc | null = null;
  deleteModal: any;
  compareModal: any;
  showSuccessAlert : boolean = false;
  showErrorAlert : boolean = false;
  showComparceComp: boolean = false;
  errorMessage: string;
  message: string;
  minRating : number = 0;
  maxRating : number = 5;
  ratingValue: number = 2.5;
  minCapacity : number = 0;
  maxCapacity : number = 100;
  capacityValue: number = 50;
  amenitiesList: string[];
  citiesList: string[];
  countriesList: string[];
  categoriesList: string[];

  public panes = [
    { name: 'Home 01', id: 'tab-01' },
    { name: 'Profile 02', id: 'tab-02' },
    { name: 'Contact 03', id: 'tab-03' }
  ];


  ngOnInit() {
    this.getAllParcs();
    this.getAllCities();

  }
  updateCompareData()
  {
    if (this.checkedParcList.length === 0)
    this.checkedParcList = this.parcs;
    this.updateMinCapacity();
    this.updateMaxCapacity();
    this.updateMinRating();
    this.updateMaxRating();
    this.fetchAmenitiesList();
    this.fetchCitiesList();
    this.fetchCountriesList();
    this.fetchCategoriesList();

  }

  toggleSelectAll() {
    // Toggle the select status for all rows
    this.isAllSelected = !this.isAllSelected;

    // Set the 'selected' property for all Parc objects
    this.parcs.forEach((parc) => (parc.selected = this.isAllSelected));
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
  compareParcs(){
    this.parcService.getAllParcs().subscribe(
      (data:Parc[]) => this.parcs = data) };

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



      updateMinCapacity() {
        const parcIds = this.checkedParcList.map(parc => parc.idParc);
        this.parcService.getMinCapacityRange(parcIds).subscribe(
          capacity => {
            this.minCapacity = capacity;
            this.capacityValue = capacity;

          },
          error => {
            console.error('Failed to get min capacity:', error);
          }
        );
      }

      updateMaxCapacity() {
        const parcIds = this.checkedParcList.map(parc => parc.idParc);
        this.parcService.getMaxCapacityRange(parcIds).subscribe(
          capacity => {
            this.maxCapacity = capacity;

          },
          error => {
            console.error('Failed to get max capacity:', error);
          }
        );
      }

      updateMinRating() {
        const parcIds = this.checkedParcList.map(parc => parc.idParc);
        this.parcService.getMinRatingRange(parcIds).subscribe(
          capacity => {
            this.minRating = capacity;
            this.ratingValue = capacity;

          },
          error => {
            console.error('Failed to get min Rating:', error);
          }
        );
      }

      updateMaxRating() {
        const parcIds = this.checkedParcList.map(parc => parc.idParc);
        this.parcService.getMaxRatingRange(parcIds).subscribe(
          capacity => {
            this.maxRating = capacity;

          },
          error => {
            console.error('Failed to get max Rating:', error);
          }
        );
      }
      fetchAmenitiesList(): void {
        const parcIds = this.checkedParcList.map(parc => parc.idParc);
        this.parcService.getAllAmenities(parcIds).subscribe(
          amenities => {
            this.amenitiesList = amenities;
          },
          error => {
            console.error('Failed to get amenities:', error);
          }
        );
      }

      fetchCitiesList(): void {
        const parcIds = this.checkedParcList.map(parc => parc.idParc);
        this.parcService.getAllCities(parcIds).subscribe(
          cities => {
            this.citiesList = cities;
          },
          error => {
            console.error('Failed to get cities:', error);
          }
        );
      }

      fetchCountriesList(): void {
        const parcIds = this.checkedParcList.map(parc => parc.idParc);
        this.parcService.getAllCountries(parcIds).subscribe(
          countries => {
            this.countriesList = countries;
          },
          error => {
            console.error('Failed to get countries:', error);
          }
        );
      }
      fetchCategoriesList(): void {
        const parcIds = this.checkedParcList.map(parc => parc.idParc);
        this.parcService.getAllCategories(parcIds).subscribe(
          countries => {
            this.categoriesList = countries;
          },
          error => {
            console.error('Failed to get countries:', error);
          }
        );
      }

  }



