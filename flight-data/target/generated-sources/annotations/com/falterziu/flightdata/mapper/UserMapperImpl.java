package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.user.UserCreateDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.entity.UserEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-13T22:41:00+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setFirstName( user.getFirstName() );
        userResponseDto.setLastName( user.getLastName() );
        userResponseDto.setPassword( user.getPassword() );
        userResponseDto.setEmail( user.getEmail() );
        userResponseDto.setRole( user.getRole() );

        return userResponseDto;
    }

    @Override
    public UserEntity toEntity(UserCreateDto userToBeCreated) {
        if ( userToBeCreated == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName( userToBeCreated.getFirstName() );
        userEntity.setLastName( userToBeCreated.getLastName() );
        userEntity.setPassword( userToBeCreated.getPassword() );
        userEntity.setEmail( userToBeCreated.getEmail() );
        userEntity.setRole( userToBeCreated.getRole() );

        return userEntity;
    }
}
