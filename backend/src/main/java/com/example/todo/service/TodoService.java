package com.example.todo.service;

import com.example.todo.dto.TodoRequest;
import com.example.todo.exception.TodoNotFoundException;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
    }

    @Transactional
    public Todo createTodo(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDone(request.getDone() != null ? request.getDone() : false);
        return todoRepository.save(todo);
    }

    @Transactional
    public Todo updateTodo(Long id, TodoRequest request) {
        Todo todo = getTodoById(id);

        if (request.getTitle() != null) {
            todo.setTitle(request.getTitle());
        }
        if (request.getDone() != null) {
            todo.setDone(request.getDone());
        }

        return todoRepository.save(todo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}
