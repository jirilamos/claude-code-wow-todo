package com.example.todo.controller;

import com.example.todo.dto.TodoRequest;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
    }

    @Test
    void getAllTodos_shouldReturnEmptyList_whenNoTodos() throws Exception {
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void createTodo_shouldCreateNewTodo() throws Exception {
        TodoRequest request = new TodoRequest();
        request.setTitle("Test Todo");

        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("Test Todo")))
                .andExpect(jsonPath("$.done", is(false)))
                .andExpect(jsonPath("$.createdAt", notNullValue()));
    }

    @Test
    void createTodo_shouldReturnBadRequest_whenTitleIsBlank() throws Exception {
        TodoRequest request = new TodoRequest();
        request.setTitle("");

        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createTodo_shouldReturnBadRequest_whenTitleIsTooLong() throws Exception {
        TodoRequest request = new TodoRequest();
        request.setTitle("a".repeat(201));

        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getTodoById_shouldReturnTodo() throws Exception {
        Todo todo = new Todo("Test Todo");
        todo = todoRepository.save(todo);

        mockMvc.perform(get("/api/todos/" + todo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(todo.getId().intValue())))
                .andExpect(jsonPath("$.title", is("Test Todo")));
    }

    @Test
    void getTodoById_shouldReturnNotFound_whenTodoDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/todos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateTodo_shouldUpdateTodo() throws Exception {
        Todo todo = new Todo("Test Todo");
        todo = todoRepository.save(todo);

        TodoRequest request = new TodoRequest();
        request.setDone(true);

        mockMvc.perform(patch("/api/todos/" + todo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.done", is(true)))
                .andExpect(jsonPath("$.title", is("Test Todo")));
    }

    @Test
    void deleteTodo_shouldDeleteTodo() throws Exception {
        Todo todo = new Todo("Test Todo");
        todo = todoRepository.save(todo);

        mockMvc.perform(delete("/api/todos/" + todo.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/todos/" + todo.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteTodo_shouldReturnNotFound_whenTodoDoesNotExist() throws Exception {
        mockMvc.perform(delete("/api/todos/999"))
                .andExpect(status().isNotFound());
    }
}
