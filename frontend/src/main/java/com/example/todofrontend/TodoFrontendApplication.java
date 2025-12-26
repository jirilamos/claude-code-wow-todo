package com.example.todofrontend;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoFrontendApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(TodoFrontendApplication.class, args);
    }
}
