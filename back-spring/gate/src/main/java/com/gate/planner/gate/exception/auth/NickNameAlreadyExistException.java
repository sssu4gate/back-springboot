package com.gate.planner.gate.exception.auth;

public class NickNameAlreadyExistException extends RuntimeException {
    public NickNameAlreadyExistException() {
        super("이미 존재하는 닉네임입니다.");
    }
}
