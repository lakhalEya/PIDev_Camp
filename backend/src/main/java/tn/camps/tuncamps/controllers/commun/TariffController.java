package tn.camps.tuncamps.controllers.commun;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entities.commun.Tariff;
import tn.camps.tuncamps.services.interfaces.commun.TariffService;

import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffController {
    @Autowired
    private TariffService tariffService;



    @GetMapping("/getOne/{id}")
    public ResponseEntity<Tariff> getTariffById(@PathVariable int id) {
        try {
            Tariff tariff = tariffService.findById(id);
            return ResponseEntity.ok(tariff);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tariff>> getAllTariffs() {
        List<Tariff> tariffs = tariffService.findAll();
        return ResponseEntity.ok(tariffs);
    }

    @PostMapping("/add")
    public ResponseEntity<Tariff> createTariff(@RequestBody Tariff tariff) {
        Tariff createdTariff = tariffService.createTariff(tariff);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTariff);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tariff> updateTariff(@PathVariable int id, @RequestBody Tariff tariff) {
        tariff.setId(id);
        try {
            Tariff updatedTariff = tariffService.updateTariff(tariff);
            return ResponseEntity.ok(updatedTariff);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTariff(@PathVariable int id) {
        try {
            tariffService.deleteTariff(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
