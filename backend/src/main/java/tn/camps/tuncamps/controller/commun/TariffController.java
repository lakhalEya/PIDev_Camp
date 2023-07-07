package tn.camps.tuncamps.controller.commun;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.service.commun.TariffService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tariff")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTariffById(@PathVariable int id) {
        Optional<Tariff> tariff = tariffService.findById(id);
        if (tariff.isPresent())
            return new ResponseEntity<>(tariff, HttpStatus.OK);
        else
            return new ResponseEntity<>("Tariff not found with id" + id, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Tariff>> getAllTariffs() {
        List<Tariff> tariffs = tariffService.findAll();
        return new ResponseEntity<>(tariffs,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTariff(@RequestBody Tariff tariff) {
        try {
            Tariff createdTariff = tariffService.createTariff(tariff);
            return new ResponseEntity<>(createdTariff,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTariff(@PathVariable int id, @RequestBody Tariff tariff) {
        tariff.setId(id);
        try {
            Tariff updatedTariff = tariffService.updateTariff(id, tariff);
            return new ResponseEntity<>(updatedTariff,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTariff(@PathVariable int id) {
        try {
            tariffService.deleteTariff(id);
            return new ResponseEntity<>("Tariff deleted successfully ",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}