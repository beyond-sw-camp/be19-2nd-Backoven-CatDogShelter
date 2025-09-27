package com.backoven.catdogshelter.common.security;

/* 원래는 entity에서 받은 비밀번호를 DTO를 통해서 전달해 주는데, 서비스쪽에서 DTO에 데이터를 받기 전에 암호화 처리를 하여 암호화 된
   비밀번호를 DTO에게 넘겨주는 형식이다. 그러니까 service에서 controller로 DTO를 통해 데이터가 넘어가기 전에 암호화 처리를 한다
   이 대 entity와 DTO의 암호화 필드명이 같으면 안된다. */

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class WebSecurity {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public WebSecurity(JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    /* 만들어진 프로바이더 bean으로 등록 */
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(authz ->
                authz.requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
        );

        http.addFilter((getAuthenticationFilter(authenticationManager())));

        return http.build();
    }

    /* 필터를 등록하기 위해 사용하는 메소드 */
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager);
    }
}
