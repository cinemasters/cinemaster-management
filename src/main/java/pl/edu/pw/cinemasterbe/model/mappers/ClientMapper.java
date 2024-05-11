package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pw.cinemasterbe.model.domain.Client;
import pl.edu.pw.cinemasterbe.model.dto.ClientDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.ClientGridDto;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientGridDto mapToGridDto(Client entity);

    @Mapping(target = "password", ignore = true)
    ClientDetailsDto mapToDetailsDto(Client entity);

    @Mapping(target = "password", ignore = true)
    Client mapToEntity(ClientDetailsDto dto);
}
