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

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Optional<Location> location = locationService.getLocationById(id);
        return location.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        try {
            Location createdLocation = locationService.createLocation(location);
            return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            String errorMessage = "A location with the same latitude and longitude already exists.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable int id, @RequestBody Location location) {
        Optional<Location> existingLocation = locationService.getLocationById(id);
        if (existingLocation.isPresent()) {
            location.setId(id);
            Location updatedLocation = locationService.createLocation(location);
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        Optional<Location> existingLocation = locationService.getLocationById(id);
        if (existingLocation.isPresent()) {
            locationService.deleteLocation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
