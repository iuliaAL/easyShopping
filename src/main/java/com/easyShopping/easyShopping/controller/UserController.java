package com.easyShopping.easyShopping.controller;

import com.easyShopping.easyShopping.service.UserService;
import com.easyShopping.easyShopping.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto userDtoResult = userService.register(userDto);
        return ResponseEntity.ok(userDtoResult);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto appUserDto) {
        UserDto appUserDtoResult = userService.login(appUserDto.getEmail(), appUserDto.getPassword());
        return ResponseEntity.ok(appUserDtoResult);
    }

}
