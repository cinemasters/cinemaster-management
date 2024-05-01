package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;

import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PageMapper {
    default <T, DTO> PageDto<DTO> map(Page<T> page, Function<T, DTO> mapper) {
        var dtos = page.stream().map(mapper).collect(Collectors.toList());

        return PageDto.<DTO>builder()
                .items(dtos)
                .pageNumber(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(Math.toIntExact(page.getTotalElements()))
                .build();
    }
}
