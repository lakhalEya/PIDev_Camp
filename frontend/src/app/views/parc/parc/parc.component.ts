import { Component, OnInit } from '@angular/core';
import { CityService } from './../services/city.service'; // Import your ParcService
import { City } from './../models/city.model'; // Import your ParcService
import {  ParcService } from './../services/parc.service'; // Import your ParcService
import { Parc } from './../models/parc.model';

@Component({
  selector: 'app-parc',
  templateUrl: './parc.component.html',
  styleUrls: ['./parc.component.scss']
})
export class ParcComponent  implements OnInit {

  constructor(private cityService: CityService, private parcService: ParcService) { }


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


  }



