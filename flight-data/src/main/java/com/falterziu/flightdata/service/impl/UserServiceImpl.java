package com.falterziu.flightdata.service.impl;

import com.falterziu.flightdata.dto.user.ChangePasswordDto;
import com.falterziu.flightdata.dto.user.UserCreateDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.dto.user.UserUpdateDto;
import com.falterziu.flightdata.entity.UserEntity;
import com.falterziu.flightdata.exception.FlightAppBadRequestException;
import com.falterziu.flightdata.exception.FlightAppNotFoundException;
import com.falterziu.flightdata.mapper.UserMapper;
import com.falterziu.flightdata.repository.BookingRepository;
import com.falterziu.flightdata.repository.UserRepository;
import com.falterziu.flightdata.service.UserService;
import com.falterziu.flightdata.util.BadRequest;
import com.falterziu.flightdata.util.Constant;
import com.falterziu.flightdata.util.NotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final BookingRepository bookingRepository;


    @Override
    public UserResponseDto addUser(UserCreateDto userCreateDto) {
        if(!userRepository.userExistsWithEmail(userCreateDto.getEmail())){
            UserEntity user =userMapper.toEntity(userCreateDto);
            user.setValidity(Boolean.TRUE);
            user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
            UserResponseDto responseDto = userMapper.toDto(userRepository.save(user));
            responseDto.setLeftFlights(Constant.maxFlightsPerYear - bookingRepository.countBookingsOfUserThisYear(LocalDate.now().getYear(), user.getId()));
           return responseDto;
        }else{
            throw new FlightAppBadRequestException(BadRequest.USER_EXISTS);
        }
    }

    @Override
    public UserResponseDto updateUser(Integer id, UserUpdateDto userUpdateDtoDto) {
       UserEntity user = userRepository.findById(id).
               orElseThrow(() -> new FlightAppNotFoundException(NotFound.USER_NOT_FOUND));
        if (userRepository.userExistsWithEmailAndId(userUpdateDtoDto.getEmail(), id)){
            throw new FlightAppBadRequestException(BadRequest.USER_EXISTS);
        }
        user.setFirstName(userUpdateDtoDto.getFirstName());
        user.setLastName(userUpdateDtoDto.getLastName());
        user.setEmail(userUpdateDtoDto.getEmail());
        UserResponseDto responseDto = userMapper.toDto(userRepository.save(user));
        responseDto.setLeftFlights(Constant.maxFlightsPerYear - bookingRepository.countBookingsOfUserThisYear(LocalDate.now().getYear(), user.getId()));
        return responseDto;
    }

    @Override
    public Page<UserResponseDto> getAllUsersSortedByName(Integer pageNumber , Integer pageSize) {
      Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "firstName"));
        List<UserResponseDto> userResponseDtoList  = userMapper.toPageDto(userRepository.findAll(pageable)).getContent();
        userResponseDtoList.forEach(userResponseDto -> userResponseDto.setLeftFlights(Constant.maxFlightsPerYear - bookingRepository.countBookingsOfUserThisYear(LocalDate.now().getYear(), userResponseDto.getId())));
        return new PageImpl<>(userResponseDtoList);
    }

    @Override
    public void deleteUser(Integer id) {
        if(userRepository.userExistsWithId(id)) {
            userRepository.delete(id);
        } else {
          throw new FlightAppNotFoundException(NotFound.USER_NOT_FOUND);
        }
    }

    @Override
    public UserResponseDto getUserById(Integer id) {
        UserEntity user = userRepository.findById(id).
                orElseThrow(() -> new FlightAppNotFoundException(NotFound.USER_NOT_FOUND));
           UserResponseDto userResponseDto = userMapper.toDto(user);
           userResponseDto.setLeftFlights(Constant.maxFlightsPerYear - bookingRepository.countBookingsOfUserThisYear(LocalDate.now().getYear(), user.getId()));
           return userResponseDto;
    }

    @Override
    public void changePassword(Integer userId, ChangePasswordDto changePasswordDto) {
        UserEntity user = userRepository.findById(userId).
                orElseThrow(() -> new FlightAppNotFoundException(NotFound.USER_NOT_FOUND));

        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            throw new FlightAppBadRequestException(BadRequest.OLD_PASS_NOT_MATCH);
        }

        if (passwordEncoder.matches(changePasswordDto.getNewPassword(), user.getPassword())) {
            throw new FlightAppBadRequestException(BadRequest.PASSWORD_SAME_AS_OLD);
        }

        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
    }
}
