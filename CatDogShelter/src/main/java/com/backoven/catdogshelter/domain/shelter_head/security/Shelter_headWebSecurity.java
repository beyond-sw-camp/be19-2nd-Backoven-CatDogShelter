//package com.backoven.catdogshelter.domain.shelter_head.security;
//
///* 원래는 entity에서 받은 비밀번호를 DTO를 통해서 전달해 주는데, 서비스쪽에서 DTO에 데이터를 받기 전에 암호화 처리를 하여 암호화 된
//   비밀번호를 DTO에게 넘겨주는 형식이다. 그러니까 service에서 controller로 DTO를 통해 데이터가 넘어가기 전에 암호화 처리를 한다
//   이 대 entity와 DTO의 암호화 필드명이 같으면 안된다. */
//
//import jakarta.servlet.Filter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.Collections;
//
//@Configuration
//public class Shelter_headWebSecurity {
//
//    private final Shelter_headJwtAuthenticationProvider jwtAuthenticationProvider;
//    private Environment env;            // JWT Token의 payload에 만료시간 만들다가 추가함
//    private final Shelter_headJwtUtil jwtUtil;
//
//    @Autowired
//    public Shelter_headWebSecurity(Shelter_headJwtAuthenticationProvider jwtAuthenticationProvider,
//                                   Environment env, Shelter_headJwtUtil jwtUtil) {
//        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
//        this.env = env;
//        this.jwtUtil = jwtUtil;
//    }
//
//    /* 만들어진 프로바이더 bean으로 등록 */
//    @Bean
//    public AuthenticationManager authenticationManager(){
//        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
//    }
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable());
//
//        http.authorizeHttpRequests(authz ->
//                authz.requestMatchers("/").permitAll()
//                    .anyRequest().authenticated()
//        )
//                /* Session 방식이 아닌 JWT Token 방식을 사용하겠다 */
//                /* Session 방식이 아닌 JWT Token 방식으로 인증된 회원(Authentication)을 Local Thread로 저장하겠다 */
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.addFilter((getAuthenticationFilter(authenticationManager())));
//
//        /* 로그인 이후 토큰을 들고 온다면 JwtFilter를 추가해서 검증하도록 함 */
//        http.addFilterBefore(new Shelter_headJwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    /* 필터를 등록하기 위해 사용하는 메소드 */
//    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
//        return new Shelter_headAuthenticationFilter(authenticationManager,env);
//    }
//}
