package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.auth.EmailAlreadyExistException;
import com.gate.planner.gate.exception.auth.NickNameAlreadyExistException;
import com.gate.planner.gate.exception.auth.UserNameAlreadyExistException;
import com.gate.planner.gate.model.dto.request.user.SignUpRequestDto;
import com.gate.planner.gate.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void signUp(SignUpRequestDto signUpRequestDto) {

        if (userRepository.existsByUserName(signUpRequestDto.getUserName()))
            throw new UserNameAlreadyExistException();
        if (userRepository.existsByEmail(signUpRequestDto.getEmail()))
            throw new EmailAlreadyExistException();
        if (userRepository.existsByNickName(signUpRequestDto.getNickname()))
            throw new NickNameAlreadyExistException();

        User user = User.builder()
                .userName(signUpRequestDto.getUserName())
                .password(signUpRequestDto.getPassword())
                .Email(signUpRequestDto.getEmail())
                .nickName(signUpRequestDto.getNickname())
                .build();

        userRepository.save(user);
    }
}
