package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Todo;
import com.app.model.Users;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	List<Todo> findByUsers(Users user);
}
