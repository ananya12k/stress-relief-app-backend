package com.example.jwt_auth;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "confirmPassword", ignore = true)
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

}

