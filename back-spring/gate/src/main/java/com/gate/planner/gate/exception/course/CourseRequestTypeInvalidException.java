package com.gate.planner.gate.exception.course;


public class CourseRequestTypeInvalidException extends RuntimeException {
    public CourseRequestTypeInvalidException() {
        super("잘못된 코스 요청 파라미터 입니다.");
    }
}
