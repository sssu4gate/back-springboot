package com.gate.planner.gate.exception.auth;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException() {
        super("이미 존재하는 Email 계정입니다.");
    }
}
