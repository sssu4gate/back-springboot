package com.gate.planner.gate.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.dto.user.UserInfoDto;
import com.gate.planner.gate.model.entity.course.CourseSearchType;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.api.ApiService;
import com.gate.planner.gate.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseService courseService;
    private final ApiService apiService;

    @Transactional
    public List<CourseResponseDto> findUserRelatedPost(int page, CourseSearchType type, int offset) {
        return courseService.findUserRelatedCourse(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName()), type, page, offset);
    }

    @Transactional
    public UserInfoDto findProfile() throws JsonProcessingException {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        return new UserInfoDto(apiService.callUserInfoAPI(user.getAccessToken(), user.getRefreshToken()), user);
    }

    /**
     * 우선은 닉네임만 변경
     */
    public String updateNickName(String newNick) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        user.setNickName(newNick);
        return newNick;
    }
}
