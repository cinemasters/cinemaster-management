package pl.edu.pw.cinemasterbe.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.Review;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Page<Review> getReviews(PageRequest pageRequest) {
        return reviewRepository.findAll(pageRequest);
    }

    public Review getReview(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public ServiceResponse<Boolean> toggleReviewVisibility(int id) {
        var review = getReview(id);

        if (review == null) {
            return ServiceResponse.<Boolean>builder().success(false).message("Review id %d does not exist.".formatted(id)).build();
        }

        var newHiddenState = review.toggleVisibility();

        reviewRepository.save(review);

        return ServiceResponse.<Boolean>builder().success(true).data(newHiddenState).build();
    }
}
