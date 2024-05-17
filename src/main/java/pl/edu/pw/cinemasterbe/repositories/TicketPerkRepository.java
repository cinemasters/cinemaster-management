package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.cinemasterbe.model.domain.ticket.TicketPerk;

@Repository
public interface TicketPerkRepository extends JpaRepository<TicketPerk, Integer> {
    boolean existsByScreeningTypeId(int id);

    boolean existsBySeatTypeId(int id);
}
