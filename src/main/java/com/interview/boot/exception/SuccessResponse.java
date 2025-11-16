package com.interview.boot.exception;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
public class SuccessResponse<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Instant timestamp = Instant.now();

    private int status;
    private String message;
    private String path;
    private T data;

    public SuccessResponse(int status, String message, String path, T data) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.data = data;
    }
}
