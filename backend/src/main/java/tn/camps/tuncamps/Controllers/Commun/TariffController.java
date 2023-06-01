package tn.camps.tuncamps.Controllers.Commun;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.Persistence.Entities.Commun.Tariff;
import tn.camps.tuncamps.Services.Interfaces.Commun.TariffService;

import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffController {
    @Autowired
    private TariffService tariffService;



    @GetMapping("/{id}")
    public ResponseEntity<Tariff> getTariffById(@PathVariable int id) {
        try {
            Tariff tariff = tariffService.findById(id);
            return ResponseEntity.ok(tariff);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Tariff>> getAllTariffs() {
        List<Tariff> tariffs = tariffService.findAll();
        return ResponseEntity.ok(tariffs);
    }

    @PostMapping
    public ResponseEntity<Tariff> createTariff(@RequestBody Tariff tariff) {
        Tariff createdTariff = tariffService.createTariff(tariff);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTariff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tariff> updateTariff(@PathVariable int id, @RequestBody Tariff tariff) {
        tariff.setId(id);
        try {
            Tariff updatedTariff = tariffService.updateTariff(tariff);
            return ResponseEntity.ok(updatedTariff);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTariff(@PathVariable int id) {
        try {
            tariffService.deleteTariff(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
