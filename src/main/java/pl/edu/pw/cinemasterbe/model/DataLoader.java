package pl.edu.pw.cinemasterbe.model;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.seeders.SeatTypeSeeder;
import pl.edu.pw.cinemasterbe.model.seeders.UserSeeder;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserSeeder userSeeder;
    private final SeatTypeSeeder seatTypeSeeder;

    @Override
    public void run(String... args) {
        userSeeder.seed();
        seatTypeSeeder.seed();
    }
}
