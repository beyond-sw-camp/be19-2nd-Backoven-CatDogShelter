package com.backoven.catdogshelter.domain.shelterhead.security;

import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.requestdto.RequestLoginDTO;
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

/**
 * 보호소장 로그인 시 동작하는 필터
 * - UsernamePasswordAuthenticationFilter 를 상속받아 커스텀 인증 처리
 * - 로그인 요청 시 토큰을 발급하는 역할
 */
@Slf4j
public class ShelterheadAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Environment env; // application.yml 속성값 (토큰 키/만료시간 등)

    public ShelterheadAuthenticationFilter(AuthenticationManager authenticationManager, Environment env) {
        // 우리가 만든 AuthenticationProvider 를 인식할 AuthenticationManager 전달
        super(authenticationManager);
        this.env = env;
    }

    /**
     * 인증 시도 메서드
     * - 클라이언트가 로그인 요청 시 실행됨
     * - JSON 요청 바디(RequestLoginDTO)를 파싱해서 UsernamePasswordAuthenticationToken 생성
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // 요청 본문(JSON)을 RequestLoginDTO로 변환
            RequestLoginDTO creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginDTO.class);

            // 입력받은 계정/비밀번호 기반으로 인증 시도
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getHeadAccount(), // 보호소장 계정
                            creds.getPwd(),         // 비밀번호
                            new ArrayList<>()       // 권한 리스트 (추후 추가 가능)
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 인증 성공 시 동작하는 메서드
     * - Authentication 객체에서 사용자 정보/권한 추출
     * - JWT 토큰 생성 후 응답 헤더에 추가
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("보호소장 로그인 성공 → Authentication 객체 반환: {}", authResult);

        // Authentication 객체에서 사용자명(id) 추출
        String id = ((User) authResult.getPrincipal()).getUsername();

        // 권한 정보 추출
        List<String> roles = authResult.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        log.info("로그인한 보호소장의 권한: {}", roles);
        log.info("토큰 만료 시간: {}", env.getProperty("token.expiration_time"));

        // JWT Claims 생성 (username + roles 포함)
        Claims claims = Jwts.claims().setSubject(id);
        claims.put("auth", List.of("ROLE_SHELTER")); // 권한

        // JWT 토큰 생성
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(env.getProperty("token.expiration_time")))) // 만료시간 설정
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret")) // secret 키로 서명
                .compact();

        // 응답 헤더에 토큰 추가
        response.addHeader("token", token);

        /**
         * 로그인 성공 후 Authentication 객체 활용 방법
         * 1) 코드 상에서: SecurityContextHolder.getContext().getAuthentication()
         * 2) 컨트롤러 매개변수: @AuthenticationPrincipal UserDetails user
         */
    }
}
