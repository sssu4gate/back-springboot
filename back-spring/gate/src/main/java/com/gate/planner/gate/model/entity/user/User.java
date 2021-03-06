package com.gate.planner.gate.model.entity.user;

import com.gate.planner.gate.util.DateUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = @Index(name = "i_nickName", columnList = "nickName"))
public class User implements UserDetails {
    @Id
    Long id;

    @Setter
    @Column(unique = true, length = 20)
    String nickName;

    @Setter
    String accessToken;

    @Setter
    String refreshToken;

    @Setter
    String imgUrl;

    Date birth;

    Date createdAt = DateUtil.toAsiaTimeZone();

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    Gender gender;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles;

    @Setter
    int likeNum;

    @Builder
    public User(Long id, Date birth, String imgUrl, String refreshToken, Gender gender, String accessToken, String nickName, List<String> roles) {
        this.id = id;
        this.birth = birth;
        this.gender = gender;
        this.imgUrl = imgUrl;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.nickName = nickName;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        /**
         * 안씀
         */
        return null;
    }

    @Override
    public String getUsername() {
        /**
         * pk를 넘기자. oAuth써서 ID가 존재하질 않으니까
         * 나중에 Long 으로 파싱할 것.
         */
        return String.valueOf(id);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
