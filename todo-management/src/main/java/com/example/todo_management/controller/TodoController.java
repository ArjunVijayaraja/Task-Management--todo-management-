package com.example.todo_management.controller;

import com.example.todo_management.dtoEntity.LoginRequestDto;
import com.example.todo_management.dtoEntity.TodosDto;
import com.example.todo_management.entity.Todo;
import com.example.todo_management.repository.TodoRepository;
import com.example.todo_management.service.TodoService;
import com.example.todo_management.utility.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor

public class TodoController {
    private TodoService todoService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    //private TodoRepository todoRepository;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword());
        authenticationManager.authenticate(token);
        String jwt = jwtUtil.generate(loginRequestDto.getUsername());
        return new ResponseEntity<>(jwt, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<TodosDto> addTodo(@RequestBody TodosDto todosDto){
        TodosDto savedDto =  todoService.addTodoTask(todosDto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("{ID}")
    public ResponseEntity<TodosDto> getTask(@PathVariable("ID") Long Id){
        TodosDto todosDto =  todoService.getTask(Id);
        return new ResponseEntity<>(todosDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodosDto>> getAllTask(){
        List<TodosDto> alltasks = todoService.getAllTodos();
        return new ResponseEntity<>(alltasks,HttpStatus.OK);
    }

    @PutMapping("{ID}")
    public ResponseEntity<TodosDto> updateTask(@PathVariable ("ID") Long id,TodosDto todosDto){
        TodosDto updatedTodoDto =  todoService.updateTodoTask(id,todosDto);
        return new ResponseEntity<>(updatedTodoDto,HttpStatus.OK);
    }



}
