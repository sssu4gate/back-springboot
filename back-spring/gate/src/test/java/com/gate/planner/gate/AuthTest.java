package com.gate.planner.gate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gate.planner.gate.factory.CommonFactory;
import com.gate.planner.gate.model.dto.auth.LoginRequestDto;
import com.gate.planner.gate.security.JwtProvider;
import com.gate.planner.gate.service.auth.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@SpringBootTest
@Transactional
public class AuthTest extends CommonFactory {

    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;

    /**
     *회원가입 테스트, 카카오 AccessToken과 RefreshToken은 받을수 없기 때문에 null 값으로
     */
    @Test
    public void signUpTest() throws ParseException {
        Assertions.assertDoesNotThrow(() -> authService.signUp(userFactory.returnSignUpRequestDto()));
    }

    /**
     * 회원가입 후 토큰생성 및 유효성 검사
     */
    @Test
    public void generateTokenTest() {

        Assertions.assertDoesNotThrow(() ->
                {
                    userFactory.returnSignUpUser();
                    jwtProvider.validateToken(
                            authService.generateToken(userFactory.getId()).getAccessToken());
                }
        );
    }

    /**
     * 카카오 AccessToken과 RefreshToken을 주기적으로 받을 수 없기 때문에
     * 일부러 오류발생
     */
    @Test
    public void loginTest() throws JsonProcessingException {
        LoginRequestDto loginRequestDto = new LoginRequestDto(null, null);
        Assertions.assertThrows(Exception.class, () -> authService.login(loginRequestDto), "올바르지 않은 토큰입니다(카카오)");
    }


}
