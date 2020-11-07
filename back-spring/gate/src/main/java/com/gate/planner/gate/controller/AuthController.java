package com.gate.planner.gate.controller;


import com.gate.planner.gate.service.api.ApiService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ApiService apiService;

    @GetMapping("/login")
    public ResponseEntity<JSONObject> getAccessToken(@RequestParam String code) {
        return apiService.callLoginAPI(code);
    }

    @GetMapping("/logout")
    public void logOut(@RequestParam String accessToken) {
        apiService.callLogOutAPI(accessToken);
    }
}
