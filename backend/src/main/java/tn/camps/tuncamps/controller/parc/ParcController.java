package tn.camps.tuncamps.controller.parc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
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

    @PutMapping("/{parcId}")
    public ResponseEntity<Parc> updateParc(@PathVariable int parcId, @RequestBody Parc parc) {
        parc.setIdParc(parcId);
        Parc updatedParc = parcService.updateParc(parc);
        return new ResponseEntity<>(updatedParc, HttpStatus.OK);
    }

    @DeleteMapping("/{parcId}")
    public ResponseEntity<Void> deleteParc(@PathVariable int parcId) {
        parcService.deleteParc(parcId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
}
