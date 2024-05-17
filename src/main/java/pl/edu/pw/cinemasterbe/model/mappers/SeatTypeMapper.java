package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import pl.edu.pw.cinemasterbe.model.domain.cinema.SeatType;
import pl.edu.pw.cinemasterbe.model.dto.cinema.SeatTypeDto;

@Mapper(componentModel = "spring")
public interface SeatTypeMapper {
    SeatTypeDto map(SeatType seatType);

    SeatType mapToEntity(SeatTypeDto dto);
}
