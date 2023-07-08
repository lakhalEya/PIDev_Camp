import { Component, OnInit } from '@angular/core';
import { CityService } from './../services/city.service'; // Import your ParcService
import { City } from './../models/city.model'; // Import your ParcService
import {  ParcService } from './../services/parc.service'; // Import your ParcService
import { Parc } from './../models/parc.model';
import { PaginationInstance } from 'ngx-pagination';

import { getStyle } from '@coreui/utils';
import { ChartjsComponent } from '@coreui/angular-chartjs';

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
  enabledparcs: Parc[];
  disabledparcs: Parc[];

  checkedParcList: Parc[]= [];
  cities: City[];
  isAllSelected = false;
  selectedParc: Parc | null = null;
  deleteModal: any;
  compareModal: any;
  showSuccessAlert : boolean = false;
  showErrorAlert : boolean = false;
  showComparceComp: boolean = false;
  keyWord: string;
  errorMessage: string;
  message: string;
  minRating : number = 0;
  maxRating : number = 5;
  ratingValue: number = 2.5;
  minCapacity : number = 0;
  maxCapacity : number = 100;
  capacityValue: number = 50;
  amenitiesList: string[]= [];
  citiesList: string[]= [];
  countriesList: string[]= [];
  categoriesList: string[]= [];
  selectedAmenitiesCheckbox: boolean = false;
  selectedAmenities: string = '';
  selectedCountry: string = '';
  selectedCountryCheckbox: boolean = false;
  selectedCityCheckbox: boolean = false;
  selectedCity: string = '';
  selectedCategoryCheckbox: boolean = false;
  selectedCategory: string = '';

  public panes = [
    { name: 'Home 01', id: 'tab-01' },
    { name: 'Profile 02', id: 'tab-02' },
    { name: 'Contact 03', id: 'tab-03' }
  ];



  ngOnInit() {
    this.findEnabledParc();
    this.findDisabledParc();
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
  disableParcs() {
    if (this.checkedParcList) {
      this.checkedParcList.forEach((parc) => {
        this.parcService.disableParc(parc.idParc).subscribe(
          () => {
            this.checkedParcList = [];
            this.showSuccessAlert = true;
            this.message= "Parc disabled successfully";
            setTimeout(() => {
              this.showSuccessAlert = false;
            }, 3000);

          },
          (error) => {
            this.showErrorAlert = true;
            this.errorMessage= error;
            setTimeout(() => {
              this.showErrorAlert = false;
            }, 3000);
            console.error(`Failed to disable parc with id ${parc.idParc}:`, error);
          }
        );
      });
    }
  }




  onAmenitiesCheckboxChange() {
    if (!this.selectedAmenitiesCheckbox) {
      this.selectedAmenities = '';

    }
    else
    {
      if (!this.checkedParcList)
        this.checkedParcList = this.enabledparcs;
      this.fetchAmenitiesList();
    }
  }

  onCountryCheckboxChange() {
    if (!this.selectedCountryCheckbox) {
      this.selectedCountry = '';
    }
    else
    {
      if (!this.checkedParcList)
        this.checkedParcList = this.enabledparcs;
      this.fetchCountriesList();
    }
  }

  onCityCheckboxChange() {
    if (!this.selectedCityCheckbox) {
      this.selectedCity = '';
    }
    else
    {
      if (!this.checkedParcList)
        this.checkedParcList = this.enabledparcs;
      this.fetchCitiesList();
    }
  }

  onCategoryCheckboxChange() {
    if (!this.selectedCategoryCheckbox) {
      this.selectedCategory = '';
    }
    else
    {
      if (!this.checkedParcList)
        this.checkedParcList = this.enabledparcs;
      this.fetchCategoriesList()
    }
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



  searchParcByKeyword(keyword: string) {
    this.parcService.searchParcByKeyword(keyword).subscribe(
      parcs => {
        this.parcs = parcs;
      },
      error => {
        console.error('Failed to search parcs by keyword:', error);
        this.showErrorAlert = true;
        this.errorMessage = error.message;
        setTimeout(() => {
          this.showErrorAlert = false;
        }, 3000);
      }
    );
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


  findDisabledParc() {
    this.parcService.findParcByDisponibility('DISABLED').subscribe(
      parcs => {
        this.disabledparcs = parcs;
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

  findParcByCategory(category: string) {
    this.parcService.findParcByCategory(category).subscribe(
      parcs => {
        this.parcs = parcs;
      },
      error => {
        this.showErrorAlert = true;
        this.errorMessage = error.message;
        setTimeout(() => {
          this.showErrorAlert = false;
        }, 3000);
        console.error('Failed to find parcs by category:', error);
      }
    );
  }

  enableParc(id: number) {

    console.log("id = ",id)
    this.parcService.enableParc(id).subscribe(
      parc => {
        this.selectedParc = null;
        this.showSuccessAlert = true;
        this.message= "Parc enabled successfully";
        setTimeout(() => {
          this.showSuccessAlert = false;
        }, 3000);

      },
      error => {
        this.showErrorAlert = true;
        this.errorMessage = error.message;
        setTimeout(() => {
          this.showErrorAlert = false;
        }, 3000);
        console.error('Failed to enable parc:', error);
      }
    );
  }



  updateParc(parcId: number, parc: Parc) {
    this.parcService.updateParc(parcId, parc).subscribe(
      updatedParc => {
        // Update the parc in the parcs array
        const index = this.parcs.findIndex(p => p.idParc === updatedParc.idParc);
        if (index !== -1) {
          this.parcs[index] = updatedParc;
        }
      },
      error => {
        this.showErrorAlert = true;
        this.errorMessage = error.message;
        setTimeout(() => {
          this.showErrorAlert = false;
        }, 3000);
        console.error('Failed to update parc:', error);
      }
    );
  }

  createParc(parc: Parc) {
    this.parcService.createParc(parc).subscribe(
      createdParc => {
        // Add the created parc to the parcs array
        this.parcs.push(createdParc);
      },
      error => {
        this.showErrorAlert = true;
        this.errorMessage = error.message;
        setTimeout(() => {
          this.showErrorAlert = false;
        }, 3000);
        console.error('Failed to create parc:', error);
      }
    );
  }




  displayAmenties(parc: Parc) {
    this.getAllParcAmenties(parc);
    console.log(this.amenitiesList);
  }

  compareParcs(): void {
    const selectedParcIds = this.enabledparcs.filter(parc => parc.selected).map(parc => parc.idParc);
    console.log("selectedParcIds",selectedParcIds)

    this.parcService.compareParcs(selectedParcIds, this.ratingValue, this.selectedAmenities, this.selectedCity, this.selectedCountry, this.selectedCategory, this.capacityValue)
      .subscribe(
        parcs => {
          console.log("parcs",parcs)

          this.enabledparcs = parcs;
          console.log("this.enabledparcs",this.enabledparcs)

          this.showSuccessAlert = true;
          this.message = 'Parcs compared successfully!';
          setTimeout(() => {
            this.showSuccessAlert = false;
          }, 3000);
          this.showComparceComp = true;
        },
        error => {
          // Handle the error response and display the error message
          this.showErrorAlert = true;
          this.errorMessage = error.message;
          setTimeout(() => {
            this.showErrorAlert = false;
          }, 3000);
        }
      );
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
              this.showErrorAlert = true;
              this.errorMessage= error;
              setTimeout(() => {
                this.showErrorAlert = false;
              }, 3000);
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

  getAllParcAmenties(parc : Parc) {

    console.log(parc);
    console.log("test getAllParcAmenties");

      this.parcService.getAllParcAmenties(parc.idParc).subscribe(
        amenities => {
          this.amenitiesList = amenities;
          console.log('Parc Amenities:', amenities);
        },
        error => {
          console.error('Failed to get parc amenities:', error);
          // Handle the error
        }
      );
  }


  resetCompare() {
    this.findEnabledParc();
    this.showComparceComp = false;
  }
}



