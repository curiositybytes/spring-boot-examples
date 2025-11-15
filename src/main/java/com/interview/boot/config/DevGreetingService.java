package com.interview.boot.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevGreetingService implements GreetingService {

    @Override
    public String greet() {
        return "Hello Developer - Running in DEV mode!";
    }
}
