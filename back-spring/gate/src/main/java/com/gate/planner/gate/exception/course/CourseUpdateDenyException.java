package com.gate.planner.gate.exception.course;

public class CourseUpdateDenyException extends RuntimeException {
    public CourseUpdateDenyException() {
        super("코스 수정 권한이 없습니다.");
    }
}
