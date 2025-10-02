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

/* 설명. Spring Security 모듈 추가 후
        default 로그인 페이지 제거 및
        인가 설정*/
@Configuration
// UserWebSecurity -> 조립공장 + 인가
@EnableWebSecurity
public class UserWebSecurity {

    private JwtAuthenticationProvider jwtAuthenticationProvider;
    // JWT 토큰의 payload에 만료시간 만들다가 추가
    private Environment env;
    private JwtUtil jwtUtil;
    private final LoginHistoryRepository loginHistoryRepository;
    @Autowired
    public UserWebSecurity(
            JwtAuthenticationProvider jwtAuthenticationProvider,
            Environment env,
            JwtUtil jwtUtil,
            LoginHistoryRepository loginHistoryRepository) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.env = env;
        this.jwtUtil = jwtUtil;
        this.loginHistoryRepository = loginHistoryRepository;
    }

    // 매니저 안에 여러개 프로바이더 등록
    /* 설명. 우리가 만든 프로바이더 bean으로 등록 */
    @Bean(name = "userAuthenticationManager")
    @Primary
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }

    @Bean
    @Order(1)
    public SecurityFilterChain userSecurity(HttpSecurity http) throws Exception {
        AuthenticationManager userAuthManager =
                new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));

        AuthenticationFilter authenticationFilter =
                new AuthenticationFilter(authenticationManager(), env, loginHistoryRepository);
        authenticationFilter.setFilterProcessesUrl("/user/login"); // 로그인 URL

        http.csrf().disable()
                .securityMatcher("/user/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/user/regist").permitAll() // 회원가입
                        .requestMatchers(HttpMethod.GET, "/user/admin/**").permitAll() // 관리자
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll() // 로그인
                        .anyRequest().authenticated() // 토큰 접근
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 안씀
                .addFilter(authenticationFilter)
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    // 필터 등록 메소드
    /* 설명. Filter를 등록하기 위해 사용하는 메소드 */
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, env, loginHistoryRepository);
    }
}
