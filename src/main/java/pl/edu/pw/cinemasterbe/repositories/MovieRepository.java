package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.cinemasterbe.model.domain.movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
