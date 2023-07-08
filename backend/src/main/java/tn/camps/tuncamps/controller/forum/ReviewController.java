package tn.camps.tuncamps.controller.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.forum.Review;
import tn.camps.tuncamps.service.forum.IReviewService;

import java.util.List;
@RestController
@RequestMapping("/review")
@CrossOrigin(origins ="http://localhost:4200")
public class ReviewController {
    @Autowired
    private IReviewService iReviewService;

    @PostMapping("/add")
    @ResponseBody
    public Review createReview(@RequestBody Review review) {
        return iReviewService.createReview(review);
    }

    @GetMapping("/show/{id}")
    public Review getReviewById(@PathVariable("id") int id) {
        return iReviewService.retrieveReview(id);
    }

    @GetMapping("/show")
    public List<Review> getAllReviews() {
        return iReviewService.retrieveAllReview();
    }

    @PutMapping("update/{id}")
    public Review updateReview(@PathVariable("id") int id, @RequestBody Review review) {
        return iReviewService.updateReview(id, review);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable("id") int id) {
        iReviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}