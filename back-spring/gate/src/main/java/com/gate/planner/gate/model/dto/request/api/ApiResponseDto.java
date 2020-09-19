package com.gate.planner.gate.model.dto.request.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {
    String title;
    String roadAddress;
    Integer mapX;
    Integer mapY;
    String category;
}
