package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.cinemasterbe.model.domain.cinema.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
