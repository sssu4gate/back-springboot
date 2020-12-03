package com.gate.planner.gate.exception.course;

public class InvalidFileTypeException extends RuntimeException {
    public InvalidFileTypeException() {
        super("이미지 형식의 파일이 아닙니다.");
    }
}
