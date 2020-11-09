package com.gate.planner.gate.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${application.jwt.secret}")
    private String secretKey;

    private final String HeaderName = "Authorization";

    private final Long accessTokenValidTime = 24 * 60 * 60 * 60 * 1000L * 30; //1달

    private final Long refreshTokenValidTime = 24 * 60 * 60 * 60 * 1000L * 180; //6달

    /**
     * 시작하기전 jwt secrey key 암호화
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Token발행 (Access,Refresh 두가지 모두 가능)
     */
    public String generateToken(String userId, List<String> roles, Long accessTokenValidTime) {

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    /**
     * AccessToken 발행
     */
    public String createAccessToken(String userId, List<String> roles) {
        return generateToken(userId, roles, accessTokenValidTime);
    }

    /**
     * RefreshToken 발행
     */
    public String createRefreshToken(String userId, List<String> roles) {
        return generateToken(userId, roles, refreshTokenValidTime);
    }

    /**
     * 토큰 유효성 검사
     * 서버 정상발행 + 만료되었는지 검사
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 권한 확인
     */
    public List<String> getAuthorities(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("roles", List.class);
    }

    /**
     * userId 확인, 우리 앱에서는 PK(카카오가 준거)
     */
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * HTTP Header의 Authorization Key값에서 가져오기(accessToken)
     * RefreshToken은 그냥 Post로 합시다.
     */
    public String getToken(HttpServletRequest request) {
        return request.getHeader(HeaderName);
    }

    /**
     * SpringSecurity 에서 필요한 Token 생성.
     */
    public Authentication getAuthenticationToken(String token) {
        return new UsernamePasswordAuthenticationToken(getUserId(token), "", getAuthorities(token).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
