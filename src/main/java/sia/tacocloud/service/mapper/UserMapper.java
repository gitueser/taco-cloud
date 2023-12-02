package sia.tacocloud.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sia.tacocloud.dto.UserDto;
import sia.tacocloud.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapToDto(User user);
}
