package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.cinemasterbe.model.domain.cinema.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
    boolean existsByCodeAndIdNot(String code, int id);

    Iterable<SeatType> findAllByPerkIdOrPerkNull(int perkId);
}
