package com.gate.planner.gate.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gate.planner.gate.model.dto.auth.LogInResponseDto;
import com.gate.planner.gate.model.dto.auth.LoginRequestDto;
import com.gate.planner.gate.model.dto.auth.SignUpRequestDto;
import com.gate.planner.gate.service.api.ApiService;
import com.gate.planner.gate.service.auth.AuthService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("로그인")
    @PostMapping("/login")
    public LogInResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws JsonProcessingException {
        return authService.login(loginRequestDto);
    }

    @ApiOperation("카카오로부터 받은 Access,Refresh 프론트한테 전달")
    @GetMapping("/kakao/login")
    public ResponseEntity<JSONObject> getAccessToken(@RequestParam String code) {
        return apiService.callLoginAPI(code);
    }

    @ApiOperation("로그아웃")
    @GetMapping("/logout")
    public void logOut(@RequestParam String accessToken) {
        apiService.callLogOutAPI(accessToken);
    }

    @ApiOperation("회원가입")
    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws ParseException {
        authService.signUp(signUpRequestDto);
    }

    @ApiOperation("회원가입 시 닉네임 중복 확인")
    @GetMapping("/exist")
    public boolean checkNickNameExist(@RequestParam String nickName) {
        return authService.checkNickNameExist(nickName);
    }
}
