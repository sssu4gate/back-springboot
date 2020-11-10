package com.gate.planner.gate.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gate.planner.gate.model.dto.auth.LogInResponseDto;
import com.gate.planner.gate.model.dto.auth.LoginRequestDto;
import com.gate.planner.gate.model.dto.auth.SignUpRequestDto;
import com.gate.planner.gate.service.api.ApiService;
import com.gate.planner.gate.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ApiService apiService;
    private final AuthService authService;

    @PostMapping("/login")
    public LogInResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws JsonProcessingException {
        return authService.login(loginRequestDto);
    }

    @GetMapping("/kakaoapi/login")
    public ResponseEntity<JSONObject> getAccessToken(@RequestParam String code) {
        return apiService.callLoginAPI(code);
    }

    @GetMapping("/logout")
    public void logOut(@RequestParam String accessToken) {
        apiService.callLogOutAPI(accessToken);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws ParseException {
        authService.signUp(signUpRequestDto);
    }

    @GetMapping("/exist")
    public boolean checkNickNameExist(@RequestParam String nickName) {
        return authService.checkNickNameExist(nickName);
    }

    @GetMapping("/userInfo")
    public Object useInfo(@RequestParam String token) throws JsonProcessingException {
        return apiService.callUserInfoAPI(token);
    }
}
