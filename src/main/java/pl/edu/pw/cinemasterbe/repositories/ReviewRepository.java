package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.cinemasterbe.model.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
