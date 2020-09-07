package com.gate.planner.gate.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Getter
public class ExceptionResponse{

    int statusCode;
    String message;
    Date date = new Date();

    public ExceptionResponse(HttpStatus status, Exception e) {
        this.message = e.getMessage();
        this.statusCode = status.value();
    }
}
