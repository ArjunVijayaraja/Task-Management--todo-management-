package com.example.todo_management.service;

import com.example.todo_management.dtoEntity.TodosDto;

import java.util.List;

public interface TodoService {
    TodosDto addTodoTask(TodosDto todosDto);
    TodosDto getTask(Long id);
    List<TodosDto> getAllTodos();
    TodosDto updateTodoTask(Long id,TodosDto todosDto);
    void deleteTodoTask(Long id);
    TodosDto completed(Long id);
    TodosDto inCompleted(Long id);


}
