package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import pl.edu.pw.cinemasterbe.model.domain.SeatType;
import pl.edu.pw.cinemasterbe.model.dto.SeatTypeDto;

@Mapper(componentModel = "spring")
public interface SeatTypeMapper {
    SeatTypeDto map(SeatType seatType);

    SeatType mapToEntity(SeatTypeDto dto);
}
