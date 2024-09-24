package com.todo.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.todo.todoapi.enitity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    
}
