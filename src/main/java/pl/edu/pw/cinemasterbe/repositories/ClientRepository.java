package pl.edu.pw.cinemasterbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.cinemasterbe.model.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
