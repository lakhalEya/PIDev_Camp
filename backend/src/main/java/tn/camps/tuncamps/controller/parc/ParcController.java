package tn.camps.tuncamps.controller.parc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.parc.ParcCategory;
import tn.camps.tuncamps.service.parc.ParcService;

import java.util.List;

@RestController
@RequestMapping("/parcs")
public class ParcController {

    @Autowired
    private ParcService parcService;

    @PostMapping
    public ResponseEntity<?> createParc(@RequestBody Parc parc) {
        try {
            Parc createdParc = parcService.createParc(parc);
            return new ResponseEntity<>(createdParc, HttpStatus.CREATED);
        }catch (IllegalArgumentException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{parcId}")
    public ResponseEntity<?> updateParc(@PathVariable int parcId, @RequestBody Parc parc) {
        try {
            parc.setIdParc(parcId);
            Parc updatedParc = parcService.updateParc(parcId,parc);
            return new ResponseEntity<>(updatedParc, HttpStatus.OK);

        }catch (IllegalArgumentException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{parcId}")
    public ResponseEntity<?> deleteParc(@PathVariable int parcId) {
        try {
            parcService.deleteParc(parcId);
        return new ResponseEntity<>("Parc " + parcId + " Deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Parc " + parcId + " is not found ", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to delete Parc: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{parcId}")
    public ResponseEntity<Parc> getParcById(@PathVariable int parcId) {
        Parc parc = parcService.getParcById(parcId);
        return new ResponseEntity<>(parc, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Parc>> getAllParcs() {
        List<Parc> parcs = parcService.getAllParcs();
        return new ResponseEntity<>(parcs, HttpStatus.OK);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableParc(@PathVariable int id) {
        try {
            Parc parc = parcService.disableParc(id);
            return new ResponseEntity<>(parc, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<?> enableLocation(@PathVariable int id) {
        try {
            Parc parc = parcService.enableParc(id);
            return new ResponseEntity<>(parc, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<?> getLocationsByCountry(@PathVariable String country) {
        List<Parc> parcList = parcService.getParcByCountry(country);
        return new ResponseEntity<>(parcList, HttpStatus.OK);

    }

    @GetMapping("/sortBy/{sortBy}")
    public ResponseEntity<?> getAllLocationsFiltredBy(@PathVariable String sortBy) {
        List<Parc> locationList = parcService.getAllParcsSorted(sortBy);
        return new ResponseEntity<>(locationList, HttpStatus.OK);

    }

    @GetMapping("/filterBy/{filterBy}/{filterValue}")
    public ResponseEntity<?> getAllLocationsFiltredBy(@PathVariable String filterBy, @PathVariable String filterValue) {
        List<Parc> locationList = parcService.getAllParcsFiltered(filterBy, filterValue);
        return new ResponseEntity<>(locationList, HttpStatus.OK);

    }

    @GetMapping("/availabe/{status}")
    public ResponseEntity<?> findLocationByDisponibility(@PathVariable Parc.ParcStatus status) {
        List<Parc> locationList = parcService.getParcByDisponibility(status);
        return new ResponseEntity<>(locationList, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> findLocationByCategory(@PathVariable ParcCategory category) {
        List<Parc> locationList = parcService.getParcByCategory(category);
        return new ResponseEntity<>(locationList, HttpStatus.OK);
    }

    @GetMapping("/amenities/{amenities}")
    public ResponseEntity<?> findLocationByAmenities(@PathVariable String amenities) {
        List<Parc> locationList = parcService.getParcByAmenities(amenities);
        return new ResponseEntity<>(locationList, HttpStatus.OK);
    }
}
