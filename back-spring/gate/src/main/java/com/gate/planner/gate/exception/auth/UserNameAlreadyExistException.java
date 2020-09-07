package com.gate.planner.gate.exception.auth;

public class UserNameAlreadyExistException extends RuntimeException {
    public UserNameAlreadyExistException() {
        super("이미 존재하는 ID 입니다.");
    }
}
