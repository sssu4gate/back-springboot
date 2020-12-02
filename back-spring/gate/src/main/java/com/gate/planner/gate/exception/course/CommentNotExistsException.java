package com.gate.planner.gate.exception.course;

public class CommentNotExistsException extends RuntimeException {
    public CommentNotExistsException() {
        super("존재하지 않는 댓글 입니다.");
    }
}
