package com.gate.planner.gate.exception.user;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("사용자 정보가 존재하지 않습니다.");
    }
}
