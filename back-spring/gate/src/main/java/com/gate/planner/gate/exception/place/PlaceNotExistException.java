package com.gate.planner.gate.exception.place;

public class PlaceNotExistException extends RuntimeException {
    public PlaceNotExistException() {
        super("존재하지 않는 장소 ID 입니다.");
    }
}
