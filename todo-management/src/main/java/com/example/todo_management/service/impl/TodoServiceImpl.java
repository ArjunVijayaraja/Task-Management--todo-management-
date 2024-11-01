package com.example.todo_management.service.impl;

import com.example.todo_management.dtoEntity.TodosDto;
import com.example.todo_management.entity.Todo;
import com.example.todo_management.exception.ResourceNotFoundException;
import com.example.todo_management.repository.TodoRepository;
import com.example.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodosDto addTodoTask(TodosDto todosDto) {
        Todo todo =  modelMapper.map(todosDto, Todo.class);
        Todo savedTodo =  todoRepository.save(todo);
        return  modelMapper.map(savedTodo,TodosDto.class);
    }

    @Override
    public TodosDto getTask(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TASK","id",id));

        return modelMapper.map(todo,TodosDto.class);
    }

    @Override
    public List<TodosDto> getAllTodos() {
        List<Todo> todos =  todoRepository.findAll();
        List<TodosDto> todoDtos = new ArrayList<>();
        todos.forEach(todo -> todoDtos.add(modelMapper.map(todo,TodosDto.class))
        );
        return todoDtos;
    }

    @Override
    public TodosDto updateTodoTask(Long id, TodosDto todosDto) {
        Todo todo   = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task","id",id));
        todo.setCompleted(todo.isCompleted());
        todo.setDescription(todo.getDescription());
        todo.setTitle(todo.getTitle());
        todo.setTitle(todo.getTitle());
        return modelMapper.map(todoRepository.save(todo),TodosDto.class);
    }

    @Override
    public void deleteTodoTask(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task","ID",id));
        todoRepository.delete(todo);
    }

    @Override
    public TodosDto completed(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task","ID",id));
        todo.setCompleted(true);

        return modelMapper.map(todoRepository.save(todo),TodosDto.class);
    }

    @Override
    public TodosDto inCompleted(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task","ID",id));
        todo.setCompleted(false);

        return modelMapper.map(todoRepository.save(todo),TodosDto.class);
    }
}
