package com.interview.boot.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;


/**
 * Concepts:
 * - Bean lifecycle - Creation -> dependency injection -> ready to use -> app shuts down
 *
 **/
@Component
public class LifecycleLogger {

    public LifecycleLogger() {
        System.out.println("LifecycleLogger: Constructor called");
    }

    @PostConstruct
    public void init() {
        System.out.println("LifecycleLogger: @PostConstruct executed — Bean is ready");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("LifecycleLogger: @PreDestroy executed — App shutting down");
    }
}
