package com.todo.todoapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.todoapi.enitity.Users;

public interface UserRepository extends JpaRepository<Users,Long>{
    Optional<Users> findByUserName(String username);
    Boolean existByEmail(String email);
    Optional<Users> findByUserNameOrEmail(String username,String email);
}
