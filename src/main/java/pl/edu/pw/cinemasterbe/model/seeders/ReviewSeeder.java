package pl.edu.pw.cinemasterbe.model.seeders;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.domain.Client;
import pl.edu.pw.cinemasterbe.model.domain.movie.Movie;
import pl.edu.pw.cinemasterbe.model.domain.movie.Review;
import pl.edu.pw.cinemasterbe.repositories.ClientRepository;
import pl.edu.pw.cinemasterbe.repositories.MovieRepository;
import pl.edu.pw.cinemasterbe.repositories.ReviewRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class ReviewSeeder {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final ClientRepository clientRepository;

    public void seed() {
        if (reviewRepository.count() > 0) {
            return;
        }

        var movies = movieRepository.findAll().stream().toList();
        var movieArray = movies.toArray(new Movie[0]);
        var clients = clientRepository.findAll().stream().toList();
        var clientArray = clients.toArray(new Client[0]);
        var faker = new Faker();

        for (int i = 0; i < 50; i++) {
            var review = Review.builder()
                    .movie(faker.options().option(movieArray))
                    .client(faker.options().option(clientArray))
                    .hidden(faker.bool().bool())
                    .rating(BigDecimal.valueOf(faker.random().nextDouble(0, 5)).setScale(2, RoundingMode.CEILING))
                    .publicationDate(faker.date().birthday().toInstant())
                    .comment(faker.restaurant().review())
                    .build();

            reviewRepository.save(review);
        }
    }
}
