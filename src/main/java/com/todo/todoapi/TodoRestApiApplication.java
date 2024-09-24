package com.todo.todoapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoRestApiApplication {

    @Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoRestApiApplication.class, args);
	}

	
}

