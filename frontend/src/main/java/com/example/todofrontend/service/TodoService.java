package com.example.todofrontend.service;

import com.example.todofrontend.model.Todo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    private final RestTemplate restTemplate;
    private final String backendUrl;

    public TodoService(@Value("${backend.url}") String backendUrl) {
        this.restTemplate = new RestTemplate();
        this.backendUrl = backendUrl;
    }

    public List<Todo> getAllTodos() {
        try {
            ResponseEntity<List<Todo>> response = restTemplate.exchange(
                    backendUrl + "/api/todos",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Todo>>() {}
            );
            return response.getBody() != null ? response.getBody() : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error fetching todos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Todo createTodo(String title) {
        try {
            Map<String, Object> request = new HashMap<>();
            request.put("title", title);
            request.put("done", false);

            return restTemplate.postForObject(
                    backendUrl + "/api/todos",
                    request,
                    Todo.class
            );
        } catch (Exception e) {
            System.err.println("Error creating todo: " + e.getMessage());
            return null;
        }
    }

    public Todo updateTodo(Long id, Boolean done) {
        try {
            Map<String, Object> request = new HashMap<>();
            request.put("done", done);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request);

            restTemplate.exchange(
                    backendUrl + "/api/todos/" + id,
                    HttpMethod.PUT,
                    entity,
                    Void.class
            );

            // Fetch and return the updated todo
            return restTemplate.getForObject(
                    backendUrl + "/api/todos/" + id,
                    Todo.class
            );

        } catch (Exception e) {
            System.err.println("Error updating todo: " + e.getMessage());
            return null;
        }
    }


    public void deleteTodo(Long id) {
        try {
            restTemplate.delete(backendUrl + "/api/todos/" + id);
        } catch (Exception e) {
            System.err.println("Error deleting todo: " + e.getMessage());
        }
    }
}
