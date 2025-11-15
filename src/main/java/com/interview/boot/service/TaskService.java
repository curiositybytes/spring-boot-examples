package com.interview.boot.service;

import com.interview.boot.dto.TaskRequest;
import com.interview.boot.entity.Task;
import com.interview.boot.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Concepts:
 * - constructor injection
 * - Service annotation
 *
 **/
@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        return repository.save(task);
    }

    public List<Task> getAll() {
        return repository.findAll();
    }
}

