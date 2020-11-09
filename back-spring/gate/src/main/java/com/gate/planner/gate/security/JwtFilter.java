package com.gate.planner.gate.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    /**
     * 실제로 jwt를 검증하는 부분 (Filter)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.getToken(request);

        if (token != null && jwtProvider.validateToken(token)) {
            Authentication authenticationToken = jwtProvider.getAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            /**
             * SecurityContextHolder를 통해서 전역변수로 정상적인 jwt를 통해 뽑아낸 User에 접근 할 수 있다.
             */
        }
        filterChain.doFilter(request, response);
    }
}
