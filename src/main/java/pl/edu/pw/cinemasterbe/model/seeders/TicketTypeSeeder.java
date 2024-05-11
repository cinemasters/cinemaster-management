package pl.edu.pw.cinemasterbe.model.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.domain.TicketType;
import pl.edu.pw.cinemasterbe.repositories.TicketTypeRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketTypeSeeder {
    private final TicketTypeRepository ticketTypeRepository;

    public void seed() {
        if (ticketTypeRepository.count() > 0) {
            return;
        }

        var ticketTypes = List.of(TicketType.builder().name("Bilet normalny").description("Bilet dla dorosłych od 18 roku życia.").price(BigDecimal.valueOf(18.12)).build(),
                TicketType.builder().name("Bilet ulgowy").description("Bilet dla młodzieży do 18 roku życia lub studentów.").price(BigDecimal.valueOf(15.20)).build(),
                TicketType.builder().name("Bilet grupowy").price(BigDecimal.valueOf(12.10)).build(),
                TicketType.builder().name("Bilet szkolny").price(BigDecimal.valueOf(9.80)).build());

        ticketTypeRepository.saveAll(ticketTypes);
    }
}
