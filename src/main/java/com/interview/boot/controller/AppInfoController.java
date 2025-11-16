package com.interview.boot.controller;

import com.interview.boot.config.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/info")
public class AppInfoController {

    private final AppProperties appProperties;

    public AppInfoController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping
    public Map<String, Object> getAppInfo() {
        return Map.of(
                "name", appProperties.getName(),
                "version", appProperties.getVersion(),
                "defaultPageSize", appProperties.getPaging().getDefaultPageSize(),
                "features", appProperties.getFeatures()
        );
    }
}