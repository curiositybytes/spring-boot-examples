package com.interview.boot.controller;

import com.interview.boot.config.GreetingService;
import com.interview.boot.dto.TaskRequest;
import com.interview.boot.entity.Task;
import com.interview.boot.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;
    private final GreetingService greetingService;

    public TaskController(TaskService service, GreetingService greetingService) {
        this.service = service;
        this.greetingService = greetingService;
    }

    @PostMapping
    public Task create(@Valid @RequestBody TaskRequest request) {
        return service.createTask(request);
    }

    @GetMapping
    public List<Task> all() {
        return service.getAll();
    }

    @GetMapping("/hello")
    public String hello() {
        return greetingService.greet();
    }
}

