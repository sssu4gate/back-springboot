package com.gate.planner.gate.service.auth;

import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.model.dto.auth.SignUpRequestDto;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.api.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ApiService apiService;
    private final UserRepository userRepository;

    public void signUp(SignUpRequestDto signUpRequestDto) {
        User user = User.builder().id(signUpRequestDto.getId())
                .accessToken(signUpRequestDto.getAccessToken())
                .refreshToken(signUpRequestDto.getRefreshToken())
                .nickName(signUpRequestDto.getNickName())
                .gender(signUpRequestDto.getGender())
                .build();

        userRepository.save(user);
    }

    public boolean checkNickNameExist(String nickName) {
        return !userRepository.existsByNickName(nickName);
    }
}
