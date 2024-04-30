package pl.edu.pw.cinemasterbe.model.mappers;

import org.mapstruct.Mapper;
import pl.edu.pw.cinemasterbe.model.domain.UserEntity;
import pl.edu.pw.cinemasterbe.model.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserEntity user);
}
