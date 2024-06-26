package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.cinemasterbe.model.domain.ticket.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {
    boolean existsByNameAndIdNot(String name, int id);
}
