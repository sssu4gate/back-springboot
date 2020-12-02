package com.gate.planner.gate.exception;

import com.gate.planner.gate.exception.auth.EmailAlreadyExistException;
import com.gate.planner.gate.exception.auth.NickNameAlreadyExistException;
import com.gate.planner.gate.exception.auth.UserNameAlreadyExistException;
import com.gate.planner.gate.exception.course.*;
import com.gate.planner.gate.exception.place.PlaceNotExistException;
import com.gate.planner.gate.exception.place.PlaceWrapperNotExistException;
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
    public ExceptionResponse AlreadyExistExceptionsHandler(Exception e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotExistException.class)
    public ExceptionResponse UserNotExistExceptionHandler(UserNotExistException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidFileTypeException.class)
    public ExceptionResponse InvalidFiletTypeExceptionHandler(InvalidFileTypeException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PlaceNotExistException.class)
    public ExceptionResponse PlaceNotExistExceptionHandler(PlaceNotExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {CourseNotExistException.class, CourseSearchTypeWrongException.class})
    public ExceptionResponse CourseNotExistExceptionHandler(CourseNotExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CourseRequestTypeInvalidException.class)
    public ExceptionResponse CourseRequestTypeInvalidExceptionHandler(CourseRequestTypeInvalidException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PlaceWrapperNotExistException.class)
    public ExceptionResponse PlaceWrapperNotExistExceptionHandler(PlaceWrapperNotExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CourseUpdateDenyException.class)
    public ExceptionResponse CourseUpdateDenyExceptionHandler(CourseUpdateDenyException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CommentNotExistsException.class)
    public ExceptionResponse CommentNotExistExceptionHandler(CommentNotExistsException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CommentAccessDenyException.class)
    public ExceptionResponse CommentUpdateDenyExceptionHandler(CommentAccessDenyException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }
}
