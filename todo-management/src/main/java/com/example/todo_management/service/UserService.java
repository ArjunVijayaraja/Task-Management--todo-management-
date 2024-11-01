package com.example.todo_management.service;

import com.example.todo_management.dtoEntity.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto deleteUser(Long userId);
    UserDto updateUser(UserDto userDto);

}
