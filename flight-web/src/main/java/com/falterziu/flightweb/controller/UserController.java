package com.falterziu.flightweb.controller;

import com.falterziu.flightdata.dto.user.UserCreateDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.dto.user.UserUpdateDto;
import com.falterziu.flightdata.service.UserService;
import com.falterziu.flightdata.util.Routes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = Routes.USERS)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserResponseDto> getAll(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return userService.getAllUsersSortedByName(pageNumber,pageSize);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserCreateDto userToBeCreated) {
        return ResponseEntity.ok(userService.addUser(userToBeCreated));
    }

    @PutMapping(Routes.BY_ID)
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Integer id,@Valid @RequestBody UserUpdateDto userToBeUpdated) {
        return ResponseEntity.ok(userService.updateUser(id,userToBeUpdated));
    }

    @DeleteMapping (Routes.BY_ID)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
