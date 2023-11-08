package com.cpe.irc5.asi2.grp1.user_manager.mapper;

import com.cpe.irc5.asi2.grp1.user_manager.dto.UserDto;
import com.cpe.irc5.asi2.grp1.user_manager.dto.UserDto.UserDtoBuilder;
import com.cpe.irc5.asi2.grp1.user_manager.model.User;
import com.cpe.irc5.asi2.grp1.user_manager.model.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-08T09:24:49+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.login( user.getLogin() );
        userDto.password( user.getPassword() );
        userDto.firstname( user.getFirstname() );
        userDto.lastname( user.getLastname() );
        userDto.wallet( user.getWallet() );

        return userDto.build();
    }

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.login( userDto.getLogin() );
        user.password( userDto.getPassword() );
        user.firstname( userDto.getFirstname() );
        user.lastname( userDto.getLastname() );
        user.wallet( userDto.getWallet() );

        return user.build();
    }
}
