package com.interview.boot.service;

import com.interview.boot.config.TaskProperties;
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

    private final TaskRepository taskRepository;
    private final TaskProperties taskProperties;

    public TaskService(TaskRepository taskRepository, TaskProperties properties) {
        this.taskRepository = taskRepository;
        this.taskProperties = properties;
    }

    public Task createTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        return taskRepository.save(task);
    }

    public Task createTask(Task task) {
        long userTaskCount = taskRepository.countByUserId(task.getUserId());
        int limit = taskProperties.getLimits().getMaxTasksPerUser();

        if (userTaskCount >= limit) {
            throw new IllegalStateException("Task limit exceeded: " + limit);
        }

        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}

