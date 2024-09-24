package com.todo.todoapi.service;

import java.util.List;

import com.todo.todoapi.dto.TodoDto;

public interface TodoService {
    
    TodoDto addToDo(TodoDto todoDto);
    TodoDto getTodoById(Long id);
    List<TodoDto> getAllToDoList();
    TodoDto updateDto(Long id,TodoDto todoDto);
    void deleteToDoById(Long id);

}
