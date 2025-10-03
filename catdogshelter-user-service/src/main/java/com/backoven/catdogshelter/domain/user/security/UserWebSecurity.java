package com.backoven.catdogshelter.domain.user.security;

import com.backoven.catdogshelter.domain.user.command.domain.repository.LoginHistoryRepository;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/**
 * Spring Security 설정 클래스
 * - 기본 로그인 페이지 제거
 * - 사용자(User) 도메인 관련 보안 설정 정의
 */
@Configuration
@EnableWebSecurity
public class UserWebSecurity {

    private final JwtAuthenticationProvider jwtAuthenticationProvider; // 사용자 인증을 위한 Provider
    private final Environment env; // 환경설정 (토큰 관련 설정값 사용)
    private final JwtUtil jwtUtil; // JWT 유틸
    private final LoginHistoryRepository loginHistoryRepository; // 로그인 기록 저장소

    @Autowired
    public UserWebSecurity(JwtAuthenticationProvider jwtAuthenticationProvider,
                           Environment env,
                           JwtUtil jwtUtil,
                           LoginHistoryRepository loginHistoryRepository) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.env = env;
        this.jwtUtil = jwtUtil;
        this.loginHistoryRepository = loginHistoryRepository;
    }

    /**
     * AuthenticationManager Bean 등록
     * - 만든 JwtAuthenticationProvider 사용
     * - @Primary: 같은 타입의 AuthenticationManager가 여러 개 있을 때 우선순위 부여
     */
    @Bean(name = "userAuthenticationManager")
    @Primary
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }

    /**
     * SecurityFilterChain 정의
     * - "/user/**" 경로에 대한 보안 규칙 설정
     * - 로그인/회원가입 요청은 모두 허용
     * - 나머지는 인증 필요
     */
    @Bean
    @Order(1) // 여러 SecurityFilterChain이 있을 때 우선순위
    public SecurityFilterChain userSecurity(HttpSecurity http) throws Exception {
        // AuthenticationManager 생성
        AuthenticationManager userAuthManager =
                new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));

        // 사용자 로그인 시 동작할 AuthenticationFilter 생성
        AuthenticationFilter authenticationFilter =
                new AuthenticationFilter(authenticationManager(), env, loginHistoryRepository);
        authenticationFilter.setFilterProcessesUrl("/user/login"); // 로그인 엔드포인트 지정

        http.csrf().disable() // CSRF 비활성화 (JWT 사용 시 불필요)
                .securityMatcher("/user/**") // "/user/**" 경로만 이 설정 적용
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/user/regist").permitAll() // 회원가입 허용
                        .requestMatchers(HttpMethod.GET, "/user/admin/**").permitAll() // 관리자용 API 허용
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll() // 로그인 허용
                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안함 (JWT 방식)
                .addFilter(authenticationFilter) // 로그인 처리 필터 추가
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class); // 요청 시 토큰 검증 필터 추가

        return http.build();
    }

    /**
     * AuthenticationFilter 생성 메소드
     * - 코드 재사용 목적으로 작성 (실제 사용은 위에서 직접 new 해서 사용 중)
     */
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, env, loginHistoryRepository);
    }
}
