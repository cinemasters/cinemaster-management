package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import pl.edu.pw.cinemasterbe.model.domain.movie.Movie;
import pl.edu.pw.cinemasterbe.model.dto.movie.MovieDto;
import pl.edu.pw.cinemasterbe.model.dto.movie.MovieGridDto;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieGridDto mapToGridDto(Movie entity);

    MovieDto mapToDetailsDto(Movie entity);

    Movie mapToEntity(MovieDto dto);
}
