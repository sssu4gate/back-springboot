package com.gate.planner.gate.model.entity.user;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum LoginType {
    GOOGLE,KAKAO
}
