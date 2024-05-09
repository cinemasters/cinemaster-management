package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import pl.edu.pw.cinemasterbe.model.domain.Movie;
import pl.edu.pw.cinemasterbe.model.dto.MovieGridDto;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieGridDto mapToGridDto(Movie entity);
}
