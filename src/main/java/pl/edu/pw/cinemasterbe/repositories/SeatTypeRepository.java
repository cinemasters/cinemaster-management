package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.cinemasterbe.model.domain.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
    boolean existsByCode(String code);
}
