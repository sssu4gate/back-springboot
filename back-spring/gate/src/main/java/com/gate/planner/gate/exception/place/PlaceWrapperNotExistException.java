package com.gate.planner.gate.exception.place;


public class PlaceWrapperNotExistException extends RuntimeException {
    public PlaceWrapperNotExistException() {
        super("Place Wrapper가 존재하지 않습니다.");
    }
}
