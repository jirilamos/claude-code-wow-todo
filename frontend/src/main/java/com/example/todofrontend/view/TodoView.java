package com.example.todofrontend.view;

import com.example.todofrontend.model.Todo;
import com.example.todofrontend.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class TodoView extends VerticalLayout {

    private final TodoService todoService;
    private final VerticalLayout todoList;
    private final TextField newTodoField;

    public TodoView(TodoService todoService) {
        this.todoService = todoService;

        setSpacing(true);
        setPadding(true);
        setMaxWidth("800px");
        setAlignItems(Alignment.CENTER);
        getStyle().set("margin", "0 auto");

        // Header
        H1 header = new H1("Todo List");
        header.getStyle().set("margin-bottom", "20px");

        // Input section
        newTodoField = new TextField();
        newTodoField.setPlaceholder("What needs to be done?");
        newTodoField.setWidth("100%");
        newTodoField.getStyle().set("flex-grow", "1");

        Button addButton = new Button("Add", new Icon(VaadinIcon.PLUS));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.addClickListener(e -> addTodo());

        HorizontalLayout inputLayout = new HorizontalLayout(newTodoField, addButton);
        inputLayout.setWidth("100%");
        inputLayout.setAlignItems(Alignment.BASELINE);

        // Todo list
        todoList = new VerticalLayout();
        todoList.setWidth("100%");
        todoList.setSpacing(true);
        todoList.setPadding(false);

        // Add components
        add(header, inputLayout, todoList);

        // Enable Enter key to add todo
        newTodoField.addKeyPressListener(event -> {
            if (event.getKey().getKeys().contains("Enter")) {
                addTodo();
            }
        });

        // Load todos
        refreshTodos();
    }

    private void addTodo() {
        String title = newTodoField.getValue().trim();

        if (title.isEmpty()) {
            showNotification("Please enter a todo", NotificationVariant.LUMO_ERROR);
            return;
        }

        if (title.length() > 200) {
            showNotification("Todo must be 200 characters or less", NotificationVariant.LUMO_ERROR);
            return;
        }

        Todo todo = todoService.createTodo(title);
        if (todo != null) {
            newTodoField.clear();
            refreshTodos();
            showNotification("Todo added", NotificationVariant.LUMO_SUCCESS);
        } else {
            showNotification("Failed to add todo", NotificationVariant.LUMO_ERROR);
        }
    }

    private void refreshTodos() {
        todoList.removeAll();
        List<Todo> todos = todoService.getAllTodos();

        if (todos.isEmpty()) {
            Div emptyMessage = new Div();
            emptyMessage.setText("No todos yet. Add one above!");
            emptyMessage.getStyle()
                    .set("color", "var(--lumo-secondary-text-color)")
                    .set("text-align", "center")
                    .set("padding", "20px");
            todoList.add(emptyMessage);
        } else {
            for (Todo todo : todos) {
                todoList.add(createTodoItem(todo));
            }
        }
    }

    private HorizontalLayout createTodoItem(Todo todo) {
        Checkbox checkbox = new Checkbox();
        checkbox.setValue(todo.getDone());
        checkbox.addValueChangeListener(e -> {
            Todo updated = todoService.updateTodo(todo.getId(), e.getValue());
            if (updated != null) {
                refreshTodos();
            } else {
                showNotification("Failed to update todo", NotificationVariant.LUMO_ERROR);
            }
        });

        Div titleDiv = new Div();
        titleDiv.setText(todo.getTitle());
        titleDiv.getStyle()
                .set("flex-grow", "1")
                .set("padding", "8px");

        if (todo.getDone()) {
            titleDiv.getStyle()
                    .set("text-decoration", "line-through")
                    .set("color", "var(--lumo-secondary-text-color)");
        }

        Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
        deleteButton.addClickListener(e -> {
            todoService.deleteTodo(todo.getId());
            refreshTodos();
            showNotification("Todo deleted", NotificationVariant.LUMO_SUCCESS);
        });

        HorizontalLayout item = new HorizontalLayout(checkbox, titleDiv, deleteButton);
        item.setWidth("100%");
        item.setAlignItems(Alignment.CENTER);
        item.getStyle()
                .set("border", "1px solid var(--lumo-contrast-10pct)")
                .set("border-radius", "var(--lumo-border-radius-m)")
                .set("padding", "8px")
                .set("background", "var(--lumo-base-color)");

        return item;
    }

    private void showNotification(String message, NotificationVariant variant) {
        Notification notification = Notification.show(message, 3000, Notification.Position.TOP_CENTER);
        notification.addThemeVariants(variant);
    }
}
