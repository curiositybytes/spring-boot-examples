package com.interview.boot.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdGreetingService implements GreetingService {

    @Override
    public String greet() {
        return "Welcome User - Running in PROD mode!";
    }
}
