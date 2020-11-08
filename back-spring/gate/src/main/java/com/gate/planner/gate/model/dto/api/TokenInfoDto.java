package com.gate.planner.gate.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfoDto {
    Long id;
    int expires_in;
    int app_id;
}
