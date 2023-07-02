package tn.camps.tuncamps.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.forum.Review;
import tn.camps.tuncamps.persistence.repository.forum.ReviewRepository;

import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review retrieveReview(int id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public List<Review> retrieveAllReview() {
        List<Review> listReview =  (List<Review>) reviewRepository.findAll();
        return listReview;
    }

    @Override
    public Review updateReview(int id, Review review) {
        Review existingReview = reviewRepository.findById(id).orElse(null);
        if (existingReview != null) {
            existingReview.setTitle(review.getTitle());
            existingReview.setContent(review.getContent());
            existingReview.setRating(review.getRating());
            existingReview.setReviewDate(review.getReviewDate());
            return reviewRepository.save(existingReview);
        }
        return null;
    }

    @Override
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}
