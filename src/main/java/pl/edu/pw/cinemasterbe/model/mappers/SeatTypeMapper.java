package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pw.cinemasterbe.model.domain.cinema.SeatType;
import pl.edu.pw.cinemasterbe.model.dto.cinema.SeatTypeDto;

@Mapper(componentModel = "spring")
public interface SeatTypeMapper {
    SeatTypeDto mapToDto(SeatType entity);

    @Mapping(target = "id", ignore = true)
    SeatType mapToEntity(SeatTypeDto dto);
}
