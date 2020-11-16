package com.gate.planner.gate.exception.course;

/**
 * 행알이의 추가 코드
 */
public class AlreadyReportedException extends RuntimeException{
    public AlreadyReportedException() { super("이미 신고한 게시물입니다."); }
}
