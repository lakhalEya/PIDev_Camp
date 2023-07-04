import { Component, OnInit } from '@angular/core';
import { CityService } from './../services/city.service'; // Import your ParcService
import { City } from './../models/city.model'; // Import your ParcService
import {  ParcService } from './../services/parc.service'; // Import your ParcService
import { Parc } from './../models/parc.model';
import { PaginationInstance } from 'ngx-pagination';


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

  public panes = [
    { name: 'Home 01', id: 'tab-01' },
    { name: 'Profile 02', id: 'tab-02' },
    { name: 'Contact 03', id: 'tab-03' }
  ];

  parcs: Parc[];
  cities: City[];

  ngOnInit() {
    this.getAllParcs();
    this.getAllCities();

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

  }



