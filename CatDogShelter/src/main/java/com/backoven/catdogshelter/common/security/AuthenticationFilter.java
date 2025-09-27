package com.backoven.catdogshelter.common.security;

/* 기존의 필더(UsernamePasswordAuthenticationFilter)에서 상속받아 우리만의 방식으로 필터링을 처리한다  */

import com.backoven.catdogshelter.domain.shelter_head.command.application.dto.RequestLoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        /* 우리가 만든 프로바이더를 알고 있는 매니저를 인지시킴 */
        super(authenticationManager);
    }

    @Override
    // 인증을 시도할 때 발생하는 메서드. 이 다음에 시큐리티를 통해 인증이 성공되면 아래에 메서드의 Authentication로 최종 이동한다
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // request.getInputStream()이 json 값이 넘어오면 RequestLoginDTO로 넘겨준다.
            RequestLoginDTO creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginDTO.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getHeadAccount(), creds.getPwd(),new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 이후 spring security가 Authentication 객체로 관리되며 넘어옴: {}", authResult);
    }
}
