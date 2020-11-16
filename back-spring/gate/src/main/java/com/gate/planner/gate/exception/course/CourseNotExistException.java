package com.gate.planner.gate.exception.course;


public class CourseNotExistException extends RuntimeException {
    public CourseNotExistException() {
        super("존재하지 않는 코스입니다.");
    }
}
