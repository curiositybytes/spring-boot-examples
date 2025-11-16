package com.interview.boot.controller;

import com.interview.boot.config.GreetingService;
import com.interview.boot.dto.TaskRequest;
import com.interview.boot.entity.Task;
import com.interview.boot.exception.SuccessResponse;
import com.interview.boot.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final GreetingService greetingService;

    public TaskController(TaskService taskService, GreetingService greetingService) {
        this.taskService = taskService;
        this.greetingService = greetingService;
    }

    @PostMapping
    public Task create(@Valid @RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Task>>> getAllTasks(HttpServletRequest request) {
        List<Task> tasks = taskService.getAll();

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        HttpStatus.OK.value(),
                        "Tasks fetched successfully",
                        request.getRequestURI(),
                        tasks
                )
        );
    }

    @GetMapping("/hello")
    public String hello() {
        return greetingService.greet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<Task>> getTaskById(@PathVariable Long id, HttpServletRequest request) {
        Task task = taskService.getTask(id); // throws ResourceNotFoundException if missing

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        HttpStatus.OK.value(),
                        "Task fetched successfully",
                        request.getRequestURI(),
                        task
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<Task>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest req,
            HttpServletRequest request) {

        Task result = taskService.updateTask(id, req);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        HttpStatus.OK.value(),
                        "Task updated successfully",
                        request.getRequestURI(),
                        result
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteTask(
            @PathVariable Long id,
            HttpServletRequest request) {

        taskService.deleteTask(id);

        return ResponseEntity.ok(
                new SuccessResponse<>(
                        HttpStatus.OK.value(),
                        "Task deleted successfully",
                        request.getRequestURI(),
                        null
                ));
    }
}

