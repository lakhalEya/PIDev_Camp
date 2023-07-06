package tn.camps.tuncamps.controller.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.service.commun.LocationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/{longitude}/{latitude}")
    public ResponseEntity<?> findLocationWithLongitudeAndLatitude(@PathVariable double longitude, @PathVariable double latitude) {
        Optional<Location> location = locationService.getLocationByCoordinates(longitude, latitude);
        if (location.isPresent())
            return new ResponseEntity<>(location, HttpStatus.OK);
        else
            return new ResponseEntity<>("No Location with longitude: " + longitude + " and latitude " + latitude, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findLocationById(@PathVariable int id) {
        Optional<Location> location = locationService.getLocationById(id);
        if (location.isPresent())
            return new ResponseEntity<>(location, HttpStatus.OK);
        else
            return new ResponseEntity<>("No Location with id " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/filterBy/{filterBy}/{filterValue}")
    public ResponseEntity<?> getAllLocationsFiltredBy(@PathVariable String filterBy, @PathVariable String filterValue) {
        List<Location> locationList = locationService.getAllLocationsFiltered(filterBy, filterValue);
        return new ResponseEntity<>(locationList, HttpStatus.OK);

    }


    @GetMapping("/sortBy/{sortBy}")
    public ResponseEntity<?> getAllLocationsFiltredBy(@PathVariable String sortBy) {
        List<Location> locationList = locationService.getAllLocationsSorted(sortBy);
        return new ResponseEntity<>(locationList, HttpStatus.OK);

    }

    @GetMapping("/country/{country}")
    public ResponseEntity<?> getLocationsByCountry(@PathVariable String country) {
        List<Location> locationList = locationService.getLocationByCountry(country);
        return new ResponseEntity<>(locationList, HttpStatus.OK);

    }

    @GetMapping("/availabe/{status}")
    public ResponseEntity<?> findLocationByDisponibility(@PathVariable boolean status) {
        List<Location> locations = locationService.getLocationByDisponibility(status);
        return new ResponseEntity<>(locations, HttpStatus.OK);
       }

    @PutMapping("/enable/{id}")
    public ResponseEntity<?> enableLocation(@PathVariable int id) {
        try {
            Location location = locationService.enableLocation(id);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableLocation(@PathVariable int id) {
        try {
            Location location = locationService.disableLocation(id);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        try {
            Location createdLocation = locationService.createLocation(location);
            return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("update/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable int id, @RequestBody Location location) {
        try {
            Location updatedLocation = locationService.updateLocation(id, location);
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{longitude}/{latitude}")
    public ResponseEntity<?> deleteLocationWithLongitudeAndLatitude(@PathVariable double longitude, @PathVariable double latitude) {
        try {
            locationService.deleteLocationByCoordinates(longitude, latitude);
            return new ResponseEntity<>("Location with longitude: " + longitude + " and latitude " + latitude + " is Deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Location with longitude: " + longitude + " and latitude " + latitude + "  is not found ", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to delete location: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable int id) {
        try {
            locationService.deleteLocation(id);
            return new ResponseEntity<>("Location " + id + " Deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Location " + id + " is not found ", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to delete location: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
