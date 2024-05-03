package pl.edu.pw.cinemasterbe.model;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.seeders.ScreeningTypeSeeder;
import pl.edu.pw.cinemasterbe.model.seeders.SeatTypeSeeder;
import pl.edu.pw.cinemasterbe.model.seeders.TicketTypeSeeder;
import pl.edu.pw.cinemasterbe.model.seeders.UserSeeder;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserSeeder userSeeder;
    private final SeatTypeSeeder seatTypeSeeder;
    private final ScreeningTypeSeeder screeningTypeSeeder;
    private final TicketTypeSeeder ticketTypeSeeder;

    @Override
    public void run(String... args) {
        userSeeder.seed();
        seatTypeSeeder.seed();
        screeningTypeSeeder.seed();
        ticketTypeSeeder.seed();
    }
}
