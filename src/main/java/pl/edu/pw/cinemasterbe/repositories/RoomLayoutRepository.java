package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.cinemasterbe.model.domain.RoomLayout;

@Repository
public interface RoomLayoutRepository extends JpaRepository<RoomLayout, Integer> {
}
