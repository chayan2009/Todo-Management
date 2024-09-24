package com.todo.todoapi.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.todo.todoapi.dto.TodoDto;
import com.todo.todoapi.enitity.Todo;
import com.todo.todoapi.repository.TodoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addToDo(TodoDto todoDto) {

        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo saveTodo = todoRepository.save(todo);

        TodoDto saveTodoDto = modelMapper.map(saveTodo, TodoDto.class);
        return saveTodoDto;

    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).get();
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllToDoList() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class)) // Correct mapping of individual `todo` objects
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateDto(Long id, TodoDto todoDto) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));

        // Update existing Todo details with new values
        existingTodo.setTitle(todoDto.getTitle());
        existingTodo.setDescription(todoDto.getDescription());
        existingTodo.setCompleted(todoDto.isCompleted());

        // Save the updated entity back to the database
        Todo updatedTodo = todoRepository.save(existingTodo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteToDoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));

        // Delete the todo
        todoRepository.delete(todo);
    }

}
