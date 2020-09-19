package com.gate.planner.gate.model.dto.request.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponseDto {

    private String address_name;
    private String place_name;
    private String category_name;
    private String x;
    private String y;


    @Builder
    public SearchResponseDto(String address_name, String place_name, String category_name, String x, String y) {
        this.address_name = address_name;
        this.category_name = category_name;
        this.place_name = place_name;
        this.x = x;
        this.y = y;
    }
}
