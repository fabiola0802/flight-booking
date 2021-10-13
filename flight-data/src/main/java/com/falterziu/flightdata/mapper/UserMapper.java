package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.user.UserCreateDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;



@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto (UserEntity user);
    UserEntity toEntity (UserCreateDto userToBeCreated);

    default Page<UserResponseDto> toPageDto(Page<UserEntity> users) {
        return users.map(this::toDto);
    }
}
