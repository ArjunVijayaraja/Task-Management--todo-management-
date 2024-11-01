package com.example.todo_management.controller;

import com.example.todo_management.dtoEntity.UserDto;
import com.example.todo_management.repository.UserRepository;
import com.example.todo_management.service.UserService;
import com.example.todo_management.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    @Qualifier("UserServiceImpl")
    private UserService userService;
//    public UserController(@Qualifier("UserServiceImpl") UserService userService){
//        this.userService = userService;
 //   }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto savedUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

}
