package com.cpe.irc5.asi2.grp1.user_manager.mapper;

import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDTO;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDTO.UserDTOBuilder;
import com.cpe.irc5.asi2.grp1.user_manager.model.User;
import com.cpe.irc5.asi2.grp1.user_manager.model.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-08T20:18:40+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDTO toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.login( user.getLogin() );
        userDTO.password( user.getPassword() );
        userDTO.firstname( user.getFirstname() );
        userDTO.lastname( user.getLastname() );
        userDTO.wallet( user.getWallet() );

        return userDTO.build();
    }

    @Override
    public User toUser(UserDTO userDto) {
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
