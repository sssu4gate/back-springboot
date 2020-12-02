package com.gate.planner.gate.exception.course;

public class CommentAccessDenyException extends RuntimeException {
    public CommentAccessDenyException() {
        super("댓글 수정 권한이 없습니다.");
    }
}
