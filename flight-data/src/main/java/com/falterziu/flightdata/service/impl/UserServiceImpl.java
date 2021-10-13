package com.falterziu.flightdata.service.impl;

import com.falterziu.flightdata.dto.user.UserCreateDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.dto.user.UserUpdateDto;
import com.falterziu.flightdata.entity.UserEntity;
import com.falterziu.flightdata.exception.FlightAppBadRequestException;
import com.falterziu.flightdata.exception.FlightAppNotFoundException;
import com.falterziu.flightdata.mapper.UserMapper;
import com.falterziu.flightdata.repository.UserRepository;
import com.falterziu.flightdata.service.UserService;
import com.falterziu.flightdata.util.BadRequest;
import com.falterziu.flightdata.util.NotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponseDto addUser(UserCreateDto userCreateDto) {
        if(!userRepository.userExistsWithEmail(userCreateDto.getEmail())){
            UserEntity user =userMapper.toEntity(userCreateDto);
            user.setValidity(Boolean.TRUE);
           return userMapper.toDto(userRepository.save(user));
        }else{
            throw new FlightAppBadRequestException(BadRequest.USER_EXISTS);
        }
    }

    @Override
    public UserResponseDto updateUser(Integer id, UserUpdateDto userUpdateDtoDto) {
       UserEntity user = Optional.of(userRepository.getById(id)).
               orElseThrow(() -> new FlightAppNotFoundException(NotFound.USER_NOT_FOUND));
        if (userRepository.userExistsWithEmailAndId(userUpdateDtoDto.getEmail(), id)){
            throw new FlightAppBadRequestException(BadRequest.USER_EXISTS);
        }
        user.setFirstName(userUpdateDtoDto.getFirstName());
        user.setLastName(userUpdateDtoDto.getLastName());
        user.setEmail(userUpdateDtoDto.getEmail());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public Page<UserResponseDto> getAllUsersSortedByName(Integer pageNumber , Integer pageSize) {
      Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "firstName"));
        return userMapper.toPageDto(userRepository.findAll(pageable));
    }

    @Override
    public void deleteUser(Integer id) {
        if(userRepository.userExistsWithId(id)) {
            userRepository.delete(id);
        } else {
          throw new FlightAppNotFoundException(NotFound.USER_NOT_FOUND);
        }
    }
}
