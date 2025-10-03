package com.backoven.catdogshelter.domain.shelterhead.security;

/**
 * Shelterhead(보호소 관리자) 전용 Spring Security 설정 클래스
 * - 인증(Authentication) & 인가(Authorization) 설정
 * - 로그인/회원가입 시 특정 URL에 맞는 보안 규칙 적용
 */

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
public class ShelterheadWebSecurity {

    private final ShelterheadJwtAuthenticationProvider jwtAuthenticationProvider; // 보호소 관리자용 AuthenticationProvider
    private final Environment env;       // 환경 변수 (yml의 token.secret, expiration_time 등 참조)
    private final ShelterheadJwtUtil jwtUtil; // JWT 유틸 (토큰 검증/파싱 등)

    @Autowired
    public ShelterheadWebSecurity(ShelterheadJwtAuthenticationProvider jwtAuthenticationProvider,
                                  Environment env, ShelterheadJwtUtil jwtUtil) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.env = env;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Shelterhead 전용 SecurityFilterChain
     * - "/shelter-head/**" 경로에만 적용됨
     * - 회원가입/로그인 URL은 모두 허용
     * - 나머지는 인증 필요
     */
    @Bean
    @Order(2) // UserWebSecurity(1번 체인) 다음 순서로 실행됨
    protected SecurityFilterChain headShelter_configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); // CSRF 비활성화

        // 보호소 관리자 전용 AuthenticationManager 생성
        AuthenticationManager authManager =
                new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));

        // ShelterheadAuthenticationFilter 생성 및 "/shelter-head/login" 경로와 연결
        ShelterheadAuthenticationFilter authenticationFilter =
                new ShelterheadAuthenticationFilter(authManager, env);
        authenticationFilter.setFilterProcessesUrl("/shelter-head/login");

        http
                // "/shelter-head/**" 경로에 대해 보안 규칙 적용
                .securityMatcher("/shelter-head/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/shelter-head/regist").permitAll()       // 회원가입 허용
                        .requestMatchers("/shelter-head/login").permitAll()        // 로그인 허용
                        .requestMatchers("/shelter-head/mypage/**").authenticated() // 마이페이지 접근은 인증 필요
                        .anyRequest().authenticated()                              // 그 외는 인증 필요
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안 함
                )
                .addFilter(authenticationFilter) // 로그인 시 동작할 AuthenticationFilter 등록
                .addFilterBefore(
                        new ShelterheadJwtFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class // UsernamePasswordAuthenticationFilter 이전에 실행
                );

        return http.build();
    }

    /**
     * AuthenticationFilter를 직접 등록할 때 사용하는 메서드
     */
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new ShelterheadAuthenticationFilter(authenticationManager, env);
    }
}
