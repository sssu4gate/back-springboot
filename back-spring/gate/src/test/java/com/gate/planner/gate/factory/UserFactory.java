package com.gate.planner.gate.factory;

import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.auth.UserNameAlreadyExistException;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.auth.SignUpRequestDto;
import com.gate.planner.gate.model.entity.user.Gender;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;


@Component
public class UserFactory {

    @Autowired
    UserRepository userRepository;

    private Long id = Long.parseLong("1522254092");
    private String nickName = "dori";
    private String birth = "1997-12-26";
    private Gender gender = Gender.M;
    private String accessToken = null;
    private String refreshToken = null;
    private String newNick = "changedNick";

    public SignUpRequestDto returnSignUpRequestDto() {
        return new SignUpRequestDto(id, nickName, gender, birth, accessToken, refreshToken);
    }

    public User returnSignUpUser() throws ParseException {
        return userRepository.save(User.builder()
                .id(id)
                .birth(DateUtil.parseDateFormat(birth))
                .gender(gender)
                .nickName(nickName)
                .accessToken(accessToken)
                .refreshToken(refreshToken).build());
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotExistException::new);
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getNewNick() {
        return newNick;
    }
}
