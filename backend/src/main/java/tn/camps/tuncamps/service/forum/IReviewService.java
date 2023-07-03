package tn.camps.tuncamps.service.forum;

import tn.camps.tuncamps.persistence.entity.forum.Review;
import java.util.List;

public interface IReviewService {
    Review createReview(Review review);
    Review retrieveReview(int id);
    List<Review> retrieveAllReview();
    Review updateReview(int id, Review review);
    void deleteReview(int id);
}
