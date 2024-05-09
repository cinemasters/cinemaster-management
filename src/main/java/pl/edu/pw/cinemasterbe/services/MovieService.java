package pl.edu.pw.cinemasterbe.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.Movie;
import pl.edu.pw.cinemasterbe.model.mappers.MovieMapper;
import pl.edu.pw.cinemasterbe.repositories.MovieRepository;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public Page<Movie> getMovies(PageRequest pageRequest) {
        return movieRepository.findAll(pageRequest);
    }
}
