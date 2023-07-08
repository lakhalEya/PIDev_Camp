package tn.camps.tuncamps.controller.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.forum.React;
import tn.camps.tuncamps.service.forum.IReactService;

import java.util.List;
@RestController
@RequestMapping("/react")
@CrossOrigin(origins ="http://localhost:4200")
public class ReactController {
    @Autowired
    private IReactService iReactService;

    @PostMapping("/add")
    @ResponseBody
    public React createReact(@RequestBody React react) {
        return iReactService.createReact(react);
    }

    @GetMapping("/show/{id}")
    public React getReactById(@PathVariable("id") int id) {
        return iReactService.retrieveReact(id);
    }


    @GetMapping("/show")
    public List<React> getAllReacts() {
        return iReactService.retrieveAllReact();
    }

    @PutMapping("update/{id}")
    public React updateReact(@PathVariable("id") int id, @RequestBody React react) {
        return iReactService.updateReact(id, react);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReact(@PathVariable("id") int id) {
        iReactService.deleteReact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
