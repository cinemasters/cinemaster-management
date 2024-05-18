package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pw.cinemasterbe.model.domain.cinema.LayoutSeat;
import pl.edu.pw.cinemasterbe.model.domain.cinema.RoomLayout;
import pl.edu.pw.cinemasterbe.model.dto.cinema.LayoutSeatDto;
import pl.edu.pw.cinemasterbe.model.dto.cinema.RoomLayoutDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.cinema.RoomLayoutGridDto;

@Mapper(componentModel = "spring")
public interface RoomLayoutMapper {
    RoomLayoutGridDto mapToGridDto(RoomLayout entity);
    
    RoomLayoutDetailsDto mapToDetailsDto(RoomLayout entity);

    @Mapping(target = "col", source = "column")
    @Mapping(target = "code", source = "type.code")
    LayoutSeatDto mapToSeatDto(LayoutSeat entity);
}
