package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pw.cinemasterbe.model.domain.cinema.Cinema;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaGridDto;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    CinemaGridDto mapToGridDto(Cinema entity);

    @Mapping(target = "id", ignore = true)
    Cinema mapToEntity(CinemaDetailsDto dto);
}
