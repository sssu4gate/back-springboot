package com.gate.planner.gate.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.api.ProfileApiDto;
import com.gate.planner.gate.model.dto.api.TokenRefreshDto;
import com.gate.planner.gate.model.dto.auth.LogInResponseDto;
import com.gate.planner.gate.model.dto.auth.LoginRequestDto;
import com.gate.planner.gate.model.dto.auth.SignUpRequestDto;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.model.entity.user.UserRole;
import com.gate.planner.gate.security.JwtProvider;
import com.gate.planner.gate.service.api.ApiService;
import com.gate.planner.gate.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
        private final ApiService apiService;
        private final UserRepository userRepository;
        private final JwtProvider jwtProvider;

        public void signUp(SignUpRequestDto signUpRequestDto) throws ParseException {
            User user = User.builder().id(signUpRequestDto.getId())
                    .accessToken(signUpRequestDto.getAccessToken())
                    .refreshToken(signUpRequestDto.getRefreshToken())
                    .nickName(signUpRequestDto.getNickName())
                    .birth(DateUtil.parseDateFormat(signUpRequestDto.getBirth()))
                    .gender(signUpRequestDto.getGender())
                    .roles(Collections.singletonList(UserRole.ROLE_USER.toString()))
                    .build();

            userRepository.save(user);
        }

        public boolean checkNickNameExist(String nickName) {
        return !userRepository.existsByNickName(nickName);
    }

    /**
     * Login시 accessToken과 refreshToken 리턴
     */
    public LogInResponseDto generateToken(Long id) {
        /**
         *  getUserName이지만 userName이 아닌, PK임. UserDetails를 Implemet하다보니 어쩔수 없이
         *  Override해야되서 걍 쓰는거
         */
        User user = userRepository.findById(id).orElseThrow(UserNotExistException::new);
        return new LogInResponseDto(jwtProvider.createAccessToken(user.getUsername(), user.getRoles()),
                jwtProvider.createRefreshToken(user.getUsername(), user.getRoles()));
    }

    public LogInResponseDto login(LoginRequestDto loginRequestDto) throws JsonProcessingException {

        User user = null;
        try {
            ProfileApiDto profile = apiService.callUserInfoAPI(loginRequestDto.getAccessToken(), loginRequestDto.getRefreshToken());
            user = userRepository.findById(profile.getId()).orElseThrow(UserNotExistException::new);
            return generateToken(user.getId());
        } catch (UserNotExistException ue) {
            throw ue;
        } catch (Exception e) {
            user = apiService.refreshTokenApi(loginRequestDto.getRefreshToken());
            return generateToken(user.getId());
        }
    }
}
