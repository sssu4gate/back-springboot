package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.auth.EmailAlreadyExistException;
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

        User user = User.builder()
                .userName(signUpRequestDto.getUserName())
                .password(signUpRequestDto.getPassword())
                .Email(signUpRequestDto.getEmail()).build();

        userRepository.save(user);
    }
}
