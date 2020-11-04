package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.user.response.UserResponseDto;
import com.gate.planner.gate.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/myinfo")
    public UserResponseDto myInfo(@RequestParam String username) {
        return userService.findOne(username);
    }
}
