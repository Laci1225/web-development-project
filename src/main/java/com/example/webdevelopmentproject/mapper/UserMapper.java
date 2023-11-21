package com.example.webdevelopmentproject.mapper;

import com.example.webdevelopmentproject.model.UserDto;
import com.example.webdevelopmentproject.persistence.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto fromUserToUserDto(User user);

    User fromUserDtoToUser(UserDto userDto);
}
