package pl.edu.pw.cinemasterbe.model.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.domain.TicketPerk;
import pl.edu.pw.cinemasterbe.model.enums.TicketPerkEnum;
import pl.edu.pw.cinemasterbe.repositories.ScreeningTypeRepository;
import pl.edu.pw.cinemasterbe.repositories.SeatTypeRepository;
import pl.edu.pw.cinemasterbe.repositories.TicketPerkRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TicketPerkSeeder {
    private final TicketPerkRepository ticketPerkRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final ScreeningTypeRepository screeningTypeRepository;

    public void seed() {
        var ticketPerks = new ArrayList<TicketPerk>();

        for (var seatType : seatTypeRepository.findAll().subList(0, 2)) {
            ticketPerks.add(TicketPerk.builder()
                    .type(TicketPerkEnum.SeatType)
                    .name(seatType.getName())
                    .charge(BigDecimal.valueOf(21.37))
                    .description("Opłata za siedzenie w %s".formatted(seatType.getName()))
                    .seatType(seatType)
                    .build());
        }

        for (var scrType : screeningTypeRepository.findAll().subList(0, 2)) {
            ticketPerks.add(TicketPerk.builder()
                    .type(TicketPerkEnum.ScreeningType)
                    .name(scrType.getName())
                    .charge(BigDecimal.valueOf(37.21))
                    .description("Opłata za seans: %s".formatted(scrType.getName()))
                    .screeningType(scrType)
                    .build());
        }

        ticketPerkRepository.saveAll(ticketPerks);
    }
}
