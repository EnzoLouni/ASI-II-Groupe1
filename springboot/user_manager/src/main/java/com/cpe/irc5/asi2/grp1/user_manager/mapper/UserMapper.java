package com.cpe.irc5.asi2.grp1.user_manager.mapper;

import com.cpe.irc5.asi2.grp1.user_manager.dto.UserDto;
import com.cpe.irc5.asi2.grp1.user_manager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "login", source = "user.login")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "firstname", source = "user.firstname")
    @Mapping(target = "lastname", source = "user.lastname")
    public abstract UserDto toUserDto(User user);

    @Mapping(target = "id", source = "userDto.id")
    @Mapping(target = "login", source = "userDto.login")
    @Mapping(target = "password", source = "userDto.password")
    @Mapping(target = "firstname", source = "userDto.firstname")
    @Mapping(target = "lastname", source = "userDto.lastname")
    public abstract User toUser(UserDto userDto);
}
