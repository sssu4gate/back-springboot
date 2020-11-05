package com.gate.planner.gate.service.user;

import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.user.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * username로 회원 조회
     */
    public UserResponseDto findOne(String userName) { return new UserResponseDto(userRepository.findByUserName(userName).orElseThrow(UserNotExistException::new)); }
}