package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pw.cinemasterbe.model.domain.movie.Review;
import pl.edu.pw.cinemasterbe.model.dto.movie.ReviewDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.movie.ReviewGridDto;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "client", source = "client.email")
    @Mapping(target = "movie", source = "movie.title")
    ReviewGridDto mapToGridDto(Review entity);

    @Mapping(target = "client", source = "client.email")
    @Mapping(target = "movie", source = "movie.title")
    ReviewDetailsDto mapToDetailsDto(Review entity);
}
