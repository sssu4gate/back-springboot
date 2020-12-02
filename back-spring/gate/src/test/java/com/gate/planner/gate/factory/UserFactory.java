package com.gate.planner.gate.factory;

import com.gate.planner.gate.dao.user.UserRepository;
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

    private Long id1 = Long.parseLong("1522254092");
    private String nickName1 = "dori";
    private String birth1 = "1997-12-26";
    private Gender gender1 = Gender.M;
    private String accessToken = null;
    private String refreshToken = null;
    private String newNick = "changedNick";

    private Long id2 = Long.parseLong("1522254091"); //testId
    private String nickName2 = "test";
    private String birth2 = "1993-04-21";
    private Gender gender2 = Gender.F;


    public SignUpRequestDto returnSignUpRequestDto1() {
        return new SignUpRequestDto(id1, nickName1, gender1, birth1, accessToken, refreshToken);
    }

    public SignUpRequestDto returnSignUpRequestDto2() {
        return new SignUpRequestDto(id2, nickName2, gender2, birth2, accessToken, refreshToken);
    }

    public User returnSignUpUser1() throws ParseException {
        return userRepository.save(User.builder()
                .id(id1)
                .birth(DateUtil.parseDateFormat(birth1))
                .gender(gender1)
                .nickName(nickName1)
                .accessToken(accessToken)
                .refreshToken(refreshToken).build());
    }

    public User returnSignUpUser2() throws ParseException {
        return userRepository.save(User.builder()
                .id(id2)
                .birth(DateUtil.parseDateFormat(birth2))
                .gender(gender2)
                .nickName(nickName2)
                .accessToken(accessToken)
                .refreshToken(refreshToken).build());
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotExistException::new);
    }

    public Long getId1() {
        return id1;
    }

    public String getNickName1() {
        return nickName1;
    }

    public String getNewNick() {
        return newNick;
    }

    public Long getId2() {
        return id2;
    }

    public String getNickName2() {
        return nickName2;
    }
}
