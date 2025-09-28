package com.backoven.catdogshelter.common.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/* 회원이 토큰을 들고왔을 때 처리하는 필터 */
/* 한 번의 요청에 단 한번만 동작하는 필터로 주로 만듦 */
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUntil;

    public JwtFilter(JwtUtil jwtUntil) {
        this.jwtUntil = jwtUntil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        log.info("헤더의 Authorization에 담긴 내용 확인: {}", authorizationHeader);

        /* 토큰을 제대로 들고 왔다면 */
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            log.info("순수 토큰 내용: {}", token);

            /* 토큰의 유효성 검사 */
            if(jwtUntil.validateToken(token)){
                Authentication authentication = jwtUntil.getAuthentication(token);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
