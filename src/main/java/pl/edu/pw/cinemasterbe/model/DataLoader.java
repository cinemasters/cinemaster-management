package pl.edu.pw.cinemasterbe.model;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.seeders.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserSeeder userSeeder;
    private final SeatTypeSeeder seatTypeSeeder;
    private final ScreeningTypeSeeder screeningTypeSeeder;
    private final TicketTypeSeeder ticketTypeSeeder;
    private final TicketPerkSeeder ticketPerkSeeder;
    private final MovieSeeder movieSeeder;
    private final ClientSeeder clientSeeder;

    @Override
    public void run(String... args) {
        userSeeder.seed();
        seatTypeSeeder.seed();
        screeningTypeSeeder.seed();
        ticketTypeSeeder.seed();
        ticketPerkSeeder.seed();
        movieSeeder.seed();
        clientSeeder.seed();
    }
}
