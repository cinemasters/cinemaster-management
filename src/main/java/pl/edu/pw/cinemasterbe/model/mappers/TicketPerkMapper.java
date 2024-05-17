package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pw.cinemasterbe.model.domain.ticket.TicketPerk;
import pl.edu.pw.cinemasterbe.model.dto.ticket.TicketPerkDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.ticket.TicketPerkGridDto;

@Mapper(componentModel = "spring")
public interface TicketPerkMapper {
    @Mapping(target = "seatTypeName", source = "seatType.name")
    @Mapping(target = "screeningTypeName", source = "screeningType.name")
    TicketPerkGridDto mapToGridDto(TicketPerk entity);

    @Mapping(target = "seatTypeId", source = "seatType.id")
    @Mapping(target = "screeningTypeId", source = "screeningType.id")
    TicketPerkDetailsDto mapToDetailsDto(TicketPerk entity);

    @Mapping(target = "id", ignore = true)
    TicketPerk mapToEntity(TicketPerkDetailsDto dto);
}
