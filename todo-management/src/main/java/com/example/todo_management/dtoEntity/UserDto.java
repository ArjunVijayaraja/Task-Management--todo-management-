package com.example.todo_management.dtoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long Id;
    private String name;
    private String email;
    private String userName;
    private  String password;
}
