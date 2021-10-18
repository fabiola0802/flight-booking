package com.falterziu.flightdata.service;

import com.falterziu.flightdata.dto.user.ChangePasswordDto;
import com.falterziu.flightdata.dto.user.UserCreateDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.dto.user.UserUpdateDto;
import org.springframework.data.domain.Page;


public interface UserService {


    UserResponseDto addUser (UserCreateDto userCreateDto);
    UserResponseDto updateUser(Integer id, UserUpdateDto user);
    Page<UserResponseDto> getAllUsersSortedByName(Integer pageNumber, Integer pagesize);
    void deleteUser(Integer id);
    UserResponseDto getUserById(Integer id);
    void changePassword(Integer userId, ChangePasswordDto changePasswordDto);

}
