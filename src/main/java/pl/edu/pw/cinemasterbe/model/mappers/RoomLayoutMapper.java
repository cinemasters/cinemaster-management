package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import pl.edu.pw.cinemasterbe.model.domain.RoomLayout;
import pl.edu.pw.cinemasterbe.model.dto.LayoutSeatDto;
import pl.edu.pw.cinemasterbe.model.dto.RoomLayoutDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.RoomLayoutGridDto;

@Mapper(componentModel = "spring")
public interface RoomLayoutMapper {
    RoomLayoutGridDto mapToGridDto(RoomLayout entity);

    default RoomLayoutDetailsDto mapToDetailsDto(RoomLayout entity) {
        return RoomLayoutDetailsDto.builder()
                .name(entity.getName())
                .rowCount(entity.getRowCount())
                .columnCount(entity.getColumnCount())
                .seats(entity.getSeats().stream().map((el) -> LayoutSeatDto.builder()
                        .col(el.getColumn())
                        .row(el.getRow())
                        .hidden(el.isHidden())
                        .code(el.getType() == null ? null : el.getType().getCode())
                        .build()).toList())
                .build();
    }
}
