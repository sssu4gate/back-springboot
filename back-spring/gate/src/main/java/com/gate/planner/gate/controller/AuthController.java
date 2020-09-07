package com.gate.planner.gate.controller;


import com.gate.planner.gate.model.dto.request.user.SignUpRequestDto;
import com.gate.planner.gate.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        authService.signUp(signUpRequestDto);
    }
}
