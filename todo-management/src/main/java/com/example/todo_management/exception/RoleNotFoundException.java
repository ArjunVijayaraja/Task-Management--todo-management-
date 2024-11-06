package com.example.todo_management.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(){
        super("No role found for this user");

    }
}
