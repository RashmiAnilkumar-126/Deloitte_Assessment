package com.app;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.model.Todo;
import com.app.model.Users;
import com.app.repository.TodoRepository;
import com.app.service.TodoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TodoManagementApplicationTest {

	@InjectMocks
	TodoServiceImpl todoServiceImpl;

	@Mock
	TodoRepository todoRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Todo todo2 = new Todo(2, "user", "todo_task_1", new Date(), false, new Date(), new Users("user"));
		when(todoRepository.findById(2L)).thenReturn(Optional.of(todo2));

	}

	@Test
	public void getTodoByIdTest() {
		Optional<Todo> optional = todoServiceImpl.getTodoById(2L);
		assertTrue(optional.isPresent());
		verify(todoRepository, times(1)).findById(2L);
	}

	@Test
	public void updateTodoTest() {
		Todo todo = new Todo(2, "user1", "todo_task_1", new Date(), false, new Date(), new Users("user1"));
		when(todoRepository.save(todo)).thenReturn(todo);
		todoServiceImpl.updateTodo(todo);
		verify(todoRepository, times(1)).save(todo);
	}

	@Test
	public void saveTodoTest() {
		Todo todo = new Todo(2, "user1", "todo_task_1", new Date(), false, new Date(), new Users("user1"));
		when(todoRepository.save(todo)).thenReturn(todo);
		todoServiceImpl.saveTodo(todo);
		verify(todoRepository, times(1)).save(todo);
	}

}
