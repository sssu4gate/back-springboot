package com.gate.planner.gate.exception;

import com.gate.planner.gate.exception.auth.EmailAlreadyExistException;
import com.gate.planner.gate.exception.auth.NickNameAlreadyExistException;
import com.gate.planner.gate.exception.auth.UserNameAlreadyExistException;
import com.gate.planner.gate.exception.course.CourseNotExistException;
import com.gate.planner.gate.exception.course.CourseRequestTypeWrongException;
import com.gate.planner.gate.exception.place.PlaceNotExistException;
import com.gate.planner.gate.exception.post.InvalidFileTypeException;
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
    @ExceptionHandler(value = {EmailAlreadyExistException.class, UserNameAlreadyExistException.class, NickNameAlreadyExistException.class})
    public ExceptionResponse AlreadyExistExceptions(Exception e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotExistException.class)
    public ExceptionResponse UserNotExistException(UserNotExistException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidFileTypeException.class)
    public ExceptionResponse InvalidFiletTypeException(InvalidFileTypeException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PlaceNotExistException.class)
    public ExceptionResponse PlaceNotExistException(PlaceNotExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {CourseNotExistException.class, CourseRequestTypeWrongException.class})
    public ExceptionResponse CourseNotExistException(CourseNotExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }
}
