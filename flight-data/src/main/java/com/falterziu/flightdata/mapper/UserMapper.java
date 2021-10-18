package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.user.UserCreateDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.entity.UserEntity;
import com.falterziu.flightdata.security.UserPrincipal;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;



@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto (UserEntity user);
    UserEntity toEntity (UserCreateDto userToBeCreated);

    UserResponseDto toDto(UserPrincipal userPrincipal);

    default Page<UserResponseDto> toPageDto(Page<UserEntity> users) {
        return users.map(this::toDto);
    }
}
