package com.gate.planner.gate.exception.post;

public class InvalidFileTypeException extends RuntimeException {
    public InvalidFileTypeException() {
        super("이미지 형식의 파일만 업로드 할 수 있습니다.");
    }
}
