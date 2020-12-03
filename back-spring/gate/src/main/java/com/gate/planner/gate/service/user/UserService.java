package com.gate.planner.gate.service.user;

import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.user.UserInfoDto;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.course.CourseService;
import com.gate.planner.gate.service.course.function.ImgUploadFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseService courseService;
    private final ImgUploadFunction imgUploadFunction;


    @Transactional
    public UserInfoDto findProfile() {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        return new UserInfoDto(user);
    }

    /**
     * 우선은 닉네임만 변경
     */
    @Transactional
    public String updateNickName(String newNick) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        user.setNickName(newNick);
        return newNick;
    }

    @Transactional
    public UserInfoDto updateUserProfileImg(MultipartFile image) throws IOException {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        String imgUrl = imgUploadFunction.StoreImgToS3(image);

        user.setImgUrl(imgUrl);
        return new UserInfoDto(user);
    }
}
