package com.backoven.catdogshelter.domain.shelterhead.security;

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
 * Shelterhead 전용 JWT 인증 필터
 * - 클라이언트 요청 헤더에 담긴 JWT 토큰을 검사
 * - 토큰 유효성 검증 후, Authentication 객체를 SecurityContext에 저장
 * - OncePerRequestFilter: 요청 당 단 한 번만 실행되는 필터
 */
@Slf4j
public class ShelterheadJwtFilter extends OncePerRequestFilter {

    private final ShelterheadJwtUtil jwtUntil; // JWT 유틸리티 클래스 (토큰 검증 및 Authentication 생성)

    public ShelterheadJwtFilter(ShelterheadJwtUtil jwtUntil) {
        this.jwtUntil = jwtUntil;
    }

    /**
     * 필터의 핵심 로직
     * - 요청 헤더에서 Authorization 값을 꺼냄
     * - "Bearer {token}" 형식이면 토큰만 추출
     * - JWT 유효성 검사 → 성공 시 Authentication 객체 생성
     * - SecurityContextHolder에 인증 정보 저장
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 요청 헤더에서 Authorization 추출
        String authorizationHeader = request.getHeader("Authorization");
        log.info("헤더의 Authorization에 담긴 내용 확인: {}", authorizationHeader);

        // Authorization 값이 있고 "Bearer " 로 시작하는 경우만 처리
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // "Bearer " 이후 순수 토큰 값만 추출
            String token = authorizationHeader.substring(7);
            log.info("순수 토큰 내용: {}", token);

            // 토큰 유효성 검사
            if (jwtUntil.validateToken(token)) {
                // 토큰에서 Authentication 객체 생성
                Authentication authentication = jwtUntil.getAuthentication(token);

                // SecurityContext에 저장 → 이후 컨트롤러/서비스에서 인증 정보 사용 가능
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 다음 필터로 진행
        filterChain.doFilter(request, response);
    }
}
