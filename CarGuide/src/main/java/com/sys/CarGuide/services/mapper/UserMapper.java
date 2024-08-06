package com.sys.CarGuide.services.mapper;

import com.sys.CarGuide.models.dto.UserDTO;
import com.sys.CarGuide.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);
}
