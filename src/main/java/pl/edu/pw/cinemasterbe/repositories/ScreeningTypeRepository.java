package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.cinemasterbe.model.domain.movie.ScreeningType;

@Repository
public interface ScreeningTypeRepository extends JpaRepository<ScreeningType, Integer> {
    boolean existsByNameAndIdNot(String name, int id);

    Iterable<ScreeningType> findAllByPerkIdOrPerkNull(int perkId);
}
