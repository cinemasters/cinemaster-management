package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import pl.edu.pw.cinemasterbe.model.domain.cinema.Cinema;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaGridDto;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    CinemaGridDto mapToGridDto(Cinema entity);
}
