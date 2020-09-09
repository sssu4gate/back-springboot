package com.gate.planner.gate.exception;

import com.gate.planner.gate.exception.auth.EmailAlreadyExistException;
import com.gate.planner.gate.exception.auth.NickNameAlreadyExistException;
import com.gate.planner.gate.exception.auth.UserNameAlreadyExistException;
import com.gate.planner.gate.exception.user.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotExistException.class)
    public ExceptionResponse UserNotExistException(UserNotExistException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NickNameAlreadyExistException.class)
    public ExceptionResponse NickNameAlreadyExistException(NickNameAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }
}
