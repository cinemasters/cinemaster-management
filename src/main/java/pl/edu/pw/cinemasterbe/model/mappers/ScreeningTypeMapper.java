package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pw.cinemasterbe.model.domain.movie.ScreeningType;
import pl.edu.pw.cinemasterbe.model.dto.movie.ScreeningTypeDto;

@Mapper(componentModel = "spring")
public interface ScreeningTypeMapper {
    ScreeningTypeDto mapToDto(ScreeningType entity);

    @Mapping(target = "id", ignore = true)
    ScreeningType mapToEntity(ScreeningTypeDto dto);
}
