package com.falterziu.flightweb.controller;

import com.falterziu.flightdata.dto.user.ChangePasswordDto;
import com.falterziu.flightdata.dto.user.UserCreateDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.dto.user.UserUpdateDto;
import com.falterziu.flightdata.security.UserPrincipal;
import com.falterziu.flightdata.service.UserService;
import com.falterziu.flightdata.util.AuthenticationFacade;
import com.falterziu.flightdata.util.Routes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = Routes.USERS)
public class UserController {

    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    public UserController(UserService userService, AuthenticationFacade authenticationFacade) {
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public Page<UserResponseDto> getAll(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return userService.getAllUsersSortedByName(pageNumber,pageSize);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserCreateDto userToBeCreated) {
        return ResponseEntity.ok(userService.addUser(userToBeCreated));
    }

    @PutMapping(Routes.BY_ID)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Integer id,@Valid @RequestBody UserUpdateDto userToBeUpdated) {
        return ResponseEntity.ok(userService.updateUser(id,userToBeUpdated));
    }

    @DeleteMapping (Routes.BY_ID)
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(Routes.BY_ID)
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id));

    }



    @PostMapping(Routes.CHANGE_PASSWORD)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authenticationFacade.getAuthentication().getPrincipal();
        userService.changePassword(userPrincipal.getId(), changePasswordDto);
        return ResponseEntity.ok().build();
    }

}
