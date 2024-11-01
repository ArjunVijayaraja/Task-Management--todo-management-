package com.example.todo_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNameAlreadyExistsException extends RuntimeException{
    private String message;

    public UserNameAlreadyExistsException(String username){
        super(String.format("The username %s is not available",username));
    }
}
