package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.Movie;
import pl.edu.pw.cinemasterbe.model.dto.MovieDto;
import pl.edu.pw.cinemasterbe.model.enums.ScreeningTypeEnum;
import pl.edu.pw.cinemasterbe.model.mappers.MovieMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.MovieRepository;

import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final ScreeningTypeService screeningTypeService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final Validator validator;

    public Page<Movie> getMovies(PageRequest pageRequest) {
        return movieRepository.findAll(pageRequest);
    }

    public Movie getMovie(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public ServiceResponse<Integer> createMovie(MovieDto dto) {
        var response = buildMovieFromDto(dto);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var newMovie = movieRepository.save(response.getData());

        return ServiceResponse.<Integer>builder().success(true).data(newMovie.getId()).build();
    }

    public ServiceResponse<Integer> updateMovie(MovieDto dto, int id) {
        var oldMovie = getMovie(id);

        if (oldMovie == null) {
            return ServiceResponse.<Integer>builder().success(false).message("Movie with id %d does not exist.".formatted(id)).build();
        }

        var response = buildMovieFromDto(dto);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var updatedMovie = response.getData();

        updatedMovie.setId(id);
        updatedMovie = movieRepository.save(updatedMovie);

        return ServiceResponse.<Integer>builder().success(true).data(updatedMovie.getId()).build();
    }

    private ServiceResponse<Movie> buildMovieFromDto(MovieDto dto) {
        var movie = movieMapper.mapToEntity(dto);
        var violations = validator.validate(movie);

        if (!violations.isEmpty()) {
            return ServiceResponse.<Movie>builder().success(false).message(buildErrorMessage(violations)).build();
        }

        var lang = movie.getOriginalLanguage();

        if (lang != null && !lang.isEmpty() && lang.length() != 2) {
            return ServiceResponse.<Movie>builder().success(false).message("The original language has invalid length: %d.".formatted(lang.length())).build();
        }

        movie.setReleaseDate(movie.getReleaseDate().truncatedTo(ChronoUnit.DAYS));

        for (var videoId : dto.getVideoTypes()) {
            var type = screeningTypeService.getScreeningTypeById(videoId).getData();

            if (type == null || type.getType() != ScreeningTypeEnum.Video || movie.getScreeningTypes().contains(type)) {
                return ServiceResponse.<Movie>builder().success(false).message("The video type id %d is invalid.".formatted(videoId)).build();
            }

            movie.addScreeningType(type);
        }

        for (var audioId : dto.getAudioTypes()) {
            var type = screeningTypeService.getScreeningTypeById(audioId).getData();

            if (type == null || type.getType() != ScreeningTypeEnum.Audio || movie.getScreeningTypes().contains(type)) {
                return ServiceResponse.<Movie>builder().success(false).message("The audio type id %d is invalid.".formatted(audioId)).build();
            }

            movie.addScreeningType(type);
        }

        return ServiceResponse.<Movie>builder().success(true).data(movie).build();
    }

    private <T> String buildErrorMessage(Set<ConstraintViolation<T>> violations) {
        var builder = new StringBuilder();

        for (var v : violations) {
            builder.append(v.getMessage()).append(" ");
        }

        return builder.toString().strip();
    }
}
