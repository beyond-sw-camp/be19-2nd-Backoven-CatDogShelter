package com.backoven.catdogshelter.domain.shelterhead.security;

/* 기존의 필더(UsernamePasswordAuthenticationFilter)에서 상속받아 우리만의 방식으로 필터링을 처리한다  */

import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.RequestLoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class Shelter_headAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Environment env;

    public Shelter_headAuthenticationFilter(AuthenticationManager authenticationManager, Environment env) {
        /* 우리가 만든 프로바이더를 알고 있는 매니저를 인지시킴 */
        super(authenticationManager);

        this.env = env;
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

        /* JWT 토큰 제작을 위한 재료 추출 */
        /* 토큰의 payload에 (id, 가진 권한들, 만료시간)을 담을 예정 */
        String id = ((User)authResult.getPrincipal()).getUsername();

        List<String> roles = authResult.getAuthorities().stream()
                                .map(role -> role.getAuthority())
                                .collect(Collectors.toList());
        log.info("List<String 형태로 뽑아낸 로그인 한 회원들 권한들: {}", roles);
        log.info("만료 시간: {}", env.getProperty("token.expiration_time"));

        /* 재료를 활용한 JWT 토큰 제작(feat. build.gradle에 라이브러기 추가 */
        Claims claims = Jwts.claims().setSubject(id);
        claims.put("auth" ,roles);

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()
                 + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);

        /* 로그인에 성공한 사람의 정보(Authentication 객체)를 활용하고 싶다면(ex. Controller) */
        /* 코드 상에서 */
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        /* 매개변수에서 */
//        @AuthenticationPrincipal UserDetails user

    }
}
