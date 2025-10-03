package com.backoven.catdogshelter.domain.user.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 인증 필터
 * - 사용자가 요청 시 JWT 토큰을 함께 전달했는지 검사
 * - 토큰이 유효하다면 Authentication 객체를 만들어 SecurityContext에 저장
 * - 한 요청당 한 번만 실행되는 필터(OncePerRequestFilter 상속)
 */
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 요청 헤더에서 Authorization 값 추출
        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization 헤더 값: {}", authorizationHeader);

        // Authorization 헤더에 "Bearer "로 시작하는 토큰이 존재할 경우 처리
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            // "Bearer " 이후의 실제 토큰 값 추출
            String token = authorizationHeader.substring(7);
            log.info("JWT 토큰 값: {}", token);

            // 토큰 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰이 유효하면 Authentication 객체 생성
                Authentication authentication = jwtUtil.getAuthentication(token);

                // SecurityContext에 인증 객체 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 다음 필터로 요청/응답 전달
        filterChain.doFilter(request, response);
    }
}
