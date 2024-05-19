package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pw.cinemasterbe.model.domain.cinema.Cinema;
import pl.edu.pw.cinemasterbe.model.domain.cinema.CinemaOpeningTime;
import pl.edu.pw.cinemasterbe.model.domain.cinema.CinemaRoom;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaGridDto;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaOpeningTimeDto;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaRoomDto;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    CinemaGridDto mapToGridDto(Cinema entity);

    @Mapping(target = "id", ignore = true)
    Cinema mapToEntity(CinemaDetailsDto dto);

    CinemaDetailsDto mapToDetailsDto(Cinema entity);

    default CinemaOpeningTimeDto mapToOpeningTimeDto(CinemaOpeningTime entity) {
        return CinemaOpeningTimeDto.builder()
                .day(entity.getDay())
                .openingTime(!entity.isClosed() ? entity.getOpeningTime().toString().substring(0, 5) : null)
                .closingTime(!entity.isClosed() ? entity.getClosingTime().toString().substring(0, 5) : null)
                .closed(entity.isClosed())
                .build();
    }

    @Mapping(target = "layoutId", source = "layout.id")
    CinemaRoomDto mapToRoomDto(CinemaRoom entity);
}
