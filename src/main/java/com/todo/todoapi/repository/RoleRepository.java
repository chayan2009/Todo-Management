package com.todo.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.todoapi.enitity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
