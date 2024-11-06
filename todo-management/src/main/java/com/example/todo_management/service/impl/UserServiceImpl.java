package com.example.todo_management.service.impl;

import com.example.todo_management.dtoEntity.UserDto;
import com.example.todo_management.entity.Role;
import com.example.todo_management.entity.User;
import com.example.todo_management.exception.UserNameAlreadyExistsException;
import com.example.todo_management.repository.RoleRepository;
import com.example.todo_management.repository.UserRepository;
import com.example.todo_management.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
       Optional<User> optionalUser = userRepository.findByUserName(userDto.getUserName());
       if(optionalUser.isPresent()){
           throw new UserNameAlreadyExistsException(userDto.getUserName());
       }
       userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
       User newUser =  modelMapper.map(userDto, User.class);
       User savedUser = userRepository.save(newUser);


       return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto deleteUser(Long userId) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }
}
