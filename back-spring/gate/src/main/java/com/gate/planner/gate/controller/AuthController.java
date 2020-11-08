package com.gate.planner.gate.controller;


import com.gate.planner.gate.model.dto.auth.SignUpRequestDto;
import com.gate.planner.gate.service.api.ApiService;
import com.gate.planner.gate.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ApiService apiService;
    private final AuthService authService;

        @GetMapping("/login")
        public ResponseEntity<JSONObject> getAccessToken(@RequestParam String code) {
            return apiService.callLoginAPI(code);
    }

    @GetMapping("/logout")
    public void logOut(@RequestParam String accessToken) {
        apiService.callLogOutAPI(accessToken);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

    }

    @GetMapping("/exist")
    public boolean checkNickNameExist(@RequestParam String nickName) {
        return authService.checkNickNameExist(nickName);
    }
}
