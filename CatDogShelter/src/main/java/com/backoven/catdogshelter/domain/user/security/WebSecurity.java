package com.backoven.catdogshelter.domain.user.security;

import com.backoven.catdogshelter.domain.user.command.domain.repository.LoginHistoryRepository;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/* 설명. Spring Security 모듈 추가 후
        default 로그인 페이지 제거 및
        인가 설정*/
@Configuration
// WebSecurity -> 조립공장 + 인가
public class WebSecurity {

    private JwtAuthenticationProvider jwtAuthenticationProvider;
    // JWT 토큰의 payload에 만료시간 만들다가 추가
    private Environment env;
    private JwtUtil jwtUtil;
    private final LoginHistoryRepository loginHistoryRepository;
    @Autowired
    public WebSecurity(
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
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests( authz ->
//                authz
//                        .requestMatchers("/**").permitAll() // ✅ 모든 요청 허용
//                        .anyRequest().permitAll())
                authz.requestMatchers(HttpMethod.POST, "/catdogshelter/users").permitAll() // 회원가입
                        .requestMatchers(HttpMethod.POST, "/catdogshelter/login").permitAll() // 로그인
                        .requestMatchers(HttpMethod.GET, "/catdogshelter/user/**").authenticated() // 조회는 인증 필요
                        .requestMatchers(HttpMethod.PUT, "/catdogshelter/user/**").authenticated() // 수정도 인증 필요
                        .requestMatchers("/catdogshelter/admin/**").permitAll()
                        .requestMatchers("/catdogshelter/**").permitAll()
                        .requestMatchers("/donation-posts/**").permitAll() // 인증 없이 접근 가능
                        .requestMatchers("/missing-posts/**").permitAll()// 인증 없이 접근 가능
                        .requestMatchers(
                                "/missing-posts/comments/**/report/**",
                                "/donation-posts/comments/**/report/**",
                                "/missing-posts/**/report",
                                "/donation-posts/**/report").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Swagger 허용
                        .anyRequest().authenticated())
                // 세션을 안쓰겟다
                /* 설명. Session 방식이 아닌 JWT Token 방식을 사용하겠다. */
                /* 설명. Session 방식이 아닌
                        JWT Token 방식으로 인증된 회원(Authentication)을
                         Local Thread로 저장하겠다.*/
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //  AuthenticationFilter 생성 + 로그인 URL 변경
        AuthenticationFilter authenticationFilter =
                new AuthenticationFilter(authenticationManager(), env, loginHistoryRepository);
        authenticationFilter.setFilterProcessesUrl("/catdogshelter/login"); // 기본 /login → /catdogshelter/login

        /* 설명. 매니지를 지닌 필터 등록 */
        http.addFilter(authenticationFilter);

        /* 설명. 로그인 이후 토큰을 들고 온다면
        *       JwtFilter를 추가해서 검증하도록 함 */
        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        return http
                .csrf().disable()
                .addFilter(authenticationFilter)
                .build();
    }

    // 필터 등록 메소드
    /* 설명. Filter를 등록하기 위해 사용하는 메소드 */
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, env, loginHistoryRepository);
    }
}
