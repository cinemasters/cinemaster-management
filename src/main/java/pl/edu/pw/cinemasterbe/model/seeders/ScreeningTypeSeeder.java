package pl.edu.pw.cinemasterbe.model.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.domain.movie.ScreeningType;
import pl.edu.pw.cinemasterbe.model.enums.ScreeningTypeEnum;
import pl.edu.pw.cinemasterbe.repositories.ScreeningTypeRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScreeningTypeSeeder {
    private final ScreeningTypeRepository repository;

    public void seed() {
        if (repository.count() > 0) {
            return;
        }

        var screeningTypes = List.of(ScreeningType.builder().name("3D").type(ScreeningTypeEnum.Video).description("Filmy 3D").build(),
                ScreeningType.builder().name("2D").type(ScreeningTypeEnum.Video).description("Filmy 2D").build(),
                ScreeningType.builder().name("Dźwięk oryginalny").type(ScreeningTypeEnum.Audio).description("Film z dźwiękiem oryginalnym").build(),
                ScreeningType.builder().name("Napisy").type(ScreeningTypeEnum.Audio).description("Film z napisami").build(),
                ScreeningType.builder().name("Dubbing").type(ScreeningTypeEnum.Audio).description("Film z dubbingiem").build());

        repository.saveAll(screeningTypes);
    }

}
