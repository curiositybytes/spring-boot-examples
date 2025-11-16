package com.interview.boot.service;

import com.interview.boot.config.TaskProperties;
import com.interview.boot.dto.TaskRequest;
import com.interview.boot.entity.Task;
import com.interview.boot.exception.ResourceNotFoundException;
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
        task.setUserId(request.getUserId());
        task.setCompleted(request.isCompleted());
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

    public Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    public Task updateTask(Long id, TaskRequest updateRequest) {
        Task existing = getTask(id);

        existing.setTitle(updateRequest.getTitle());
        existing.setUserId(updateRequest.getUserId());
        existing.setCompleted(updateRequest.isCompleted());

        return taskRepository.save(existing);
    }

    public void deleteTask(Long id) {
        Task existing = getTask(id);
        taskRepository.delete(existing);
    }
}

