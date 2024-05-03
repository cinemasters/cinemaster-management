package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import pl.edu.pw.cinemasterbe.model.domain.TicketType;
import pl.edu.pw.cinemasterbe.model.dto.TicketTypeDto;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {
    TicketTypeDto mapToDto(TicketType entity);

    TicketType mapToEntity(TicketTypeDto dto);
}
