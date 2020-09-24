package com.gate.planner.gate.model.dto.course.response;

import com.gate.planner.gate.model.dto.place.PlaceWrapperResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class CourseResponseDetailDto {

    Long id;

    String title;

    String content;

    String userName;

    @Setter
    int commentNum = 0;

    @Setter
    int likeNum = 0;

    int totalCost;

    List<PlaceWrapperResponseDto> places;
    List<String> memos;

    @Builder
    public CourseResponseDetailDto(Long id, String title, String content, String userName, int totalCost, List<PlaceWrapperResponseDto> places, List<String> memos) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.totalCost = totalCost;
        this.places = places;
        this.memos = memos;
    }
}
