package com.todo.todoapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todoapi.dto.TodoDto;
import com.todo.todoapi.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/todo")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<TodoDto> addToDo(@RequestBody TodoDto todoDto) {
        TodoDto saveDto = todoService.addToDo(todoDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping({ "/getTodoById/id" })
    public ResponseEntity<TodoDto> getToDo(@PathVariable("id") Long id) {
        TodoDto saveDto = todoService.getTodoById(id);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/alltodos")
    public ResponseEntity<List<TodoDto>> getToDos() {
        List<TodoDto> todoDtos = todoService.getAllToDoList();
        return ResponseEntity.ok(todoDtos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateToDo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        TodoDto updatedTodo = todoService.updateDto(id, todoDto);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteToDoById(@PathVariable Long id) {
        todoService.deleteToDoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
