package com.interview.boot.repository;

import com.interview.boot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Concepts:
 * - This uses Spring Boot Auto-Config to auto-create the Repository bean.
 *
 **/
public interface TaskRepository extends JpaRepository<Task, Long> {
}