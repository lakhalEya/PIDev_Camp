import { Component , OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ParcService} from "../services/parc.service";
import { LocationService } from "../services/location.service";

import {freeSet} from "@coreui/icons";
import {Parc} from "../models/parc.model";
import {Location} from "../models/location.model";
import {City} from "../models/city.model";

@Component({
  selector: 'app-create-form',
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.scss']
})
export class CreateFormComponent implements OnInit{
  icons = freeSet ;

  parcForm: FormGroup;
  amenitiesList: string[] = [];
  locationList: Location[];
  showErrorAlert : boolean = false;
  errorMessage: string;
  showSuccessAlert : boolean = false;
  message: string;
  showCustomAmenities: boolean = false;
  showCustomLocation: boolean = false;
  location: Location;
  constructor(
    private formBuilder: FormBuilder,
    private parcService: ParcService,
    private locationService: LocationService
  ) {}



ngOnInit(): void {
  this.fetchAmenitiesList();
  this.getAllLocations();

  this.parcForm = this.formBuilder.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    maxCapacity: ['', [Validators.required, Validators.min(10)]],
    location: this.formBuilder.group({
      city: ['', Validators.required],
      latitude: ['', Validators.required],
      longitude: ['', Validators.required]
    }),
    imageURL: [''],
    rating: ['', Validators.required],
    owner: [''],
    amenities: [[]]
  });
}
  toggleCustomAmenities() {
    this.showCustomAmenities =  true;
  }

  toggleCustomLocation(){
    this.showCustomLocation = true;
  }

  getAllLocations(){
      this.locationService.getAllLocations().subscribe(
        (data:Location[]) => this.locationList = data)
  }
  onSubmit() {
    if (this.parcForm.invalid) {
      console.log("invalid")
      return;
    }

    // Call your ParcService method to create the parc
    this.parcService.createParc(this.parcForm.value).subscribe(
      (response) => {
        // Handle success
        console.log('Parc created successfully:', response);
      },
      (error) => {
        // Handle error
        console.error('Failed to create parc:', error);
      }
    );
  }

  getLocationById(locationId: number): void {
    if (locationId) {
      this.locationService.getLocationById(locationId).subscribe(
        (location) => {
          this.location = location;
        },
        (error) => {
          console.error('Failed to retrieve location:', error);
        }
      );
    }
  }

  fetchAmenitiesList(): void {
    this.parcService.getParcsAmenities().subscribe(
      amenities => {
        this.amenitiesList = amenities;
      },
      error => {
        console.error('Failed to get amenities:', error);
      }
    );
  }
}
