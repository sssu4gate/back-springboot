package com.gate.planner.gate.service.auth;

import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.auth.LogInResponseDto;
import com.gate.planner.gate.model.dto.auth.SignUpRequestDto;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.security.JwtProvider;
import com.gate.planner.gate.service.api.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ApiService apiService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

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

    public LogInResponseDto login(Long id) {
        /**
         *  getUserName이지만 userName이 아닌, PK임. UserDetails를 Implemet하다보니 어쩔수 없이
         *  Override해야되서 걍 쓰는거
         */
        User user = userRepository.findById(id).orElseThrow(UserNotExistException::new);
        return new LogInResponseDto(jwtProvider.createAccessToken(user.getUsername(), user.getRoles()),
                jwtProvider.createRefreshToken(user.getUsername(), user.getRoles()));
    }
}
