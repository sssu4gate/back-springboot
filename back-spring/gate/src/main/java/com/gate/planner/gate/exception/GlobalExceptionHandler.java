package com.gate.planner.gate.exception;

import com.gate.planner.gate.exception.auth.EmailAlreadyExistException;
import com.gate.planner.gate.exception.auth.UserNameAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public ExceptionResponse EmailAlreadyExistException(EmailAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserNameAlreadyExistException.class)
    public ExceptionResponse UserNameAlreadyExistException(UserNameAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }
}
