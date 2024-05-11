package pl.edu.pw.cinemasterbe.model.seeders;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.domain.Movie;
import pl.edu.pw.cinemasterbe.model.enums.AgeRestrictionEnum;
import pl.edu.pw.cinemasterbe.model.enums.ScreeningTypeEnum;
import pl.edu.pw.cinemasterbe.repositories.MovieRepository;
import pl.edu.pw.cinemasterbe.repositories.ScreeningTypeRepository;

import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class MovieSeeder {
    private final MovieRepository movieRepository;
    private final ScreeningTypeRepository screeningTypeRepository;

    public void seed() {
        if (movieRepository.count() > 0) {
            return;
        }

        var faker = new Faker();
        var scrTypes = screeningTypeRepository.findAll();
        var videoTypes = scrTypes.stream().filter(el -> el.getType() == ScreeningTypeEnum.Video).toList();
        var audioTypes = scrTypes.stream().filter(el -> el.getType() == ScreeningTypeEnum.Audio).toList();

        for (int i = 0; i < 10; i++) {
            var movie = Movie.builder()
                    .ageRestriction(faker.options().option(AgeRestrictionEnum.class))
                    .genre(faker.book().genre())
                    .originalLanguage(faker.languageCode().iso639())
                    .cast(faker.oscarMovie().actor())
                    .length(faker.random().nextInt(1, 200))
                    .director(faker.name().fullName())
                    .releaseDate(faker.date().birthdayLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant())
                    .title(faker.oscarMovie().movieName())
                    .originalTitle(faker.oscarMovie().movieName())
                    .description(faker.lorem().sentence(30))
                    .production("%s, %s".formatted(faker.country().name(), faker.oscarMovie().getYear().substring(1)))
                    .visible(true)
                    .build();

            movie.addScreeningType(videoTypes.getFirst());
            movie.addScreeningType(audioTypes.getFirst());

            movieRepository.save(movie);
        }
    }
}
