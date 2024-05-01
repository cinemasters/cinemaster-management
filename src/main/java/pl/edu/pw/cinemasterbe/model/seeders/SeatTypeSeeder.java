package pl.edu.pw.cinemasterbe.model.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.domain.SeatType;
import pl.edu.pw.cinemasterbe.repositories.SeatTypeRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SeatTypeSeeder {
    private final SeatTypeRepository seatTypeRepository;

    public void seed() {
        var seatTypes = new ArrayList<SeatType>();

        for (int i = 1; i < 8; i++) {
            seatTypes.add(SeatType.builder()
                    .code(String.valueOf(i).charAt(0))
                    .name("Strefa %d".formatted(i))
                    .description("Strefa biletowa numer %d".formatted(i))
                    .build());
        }

        seatTypeRepository.saveAll(seatTypes);
    }
}
