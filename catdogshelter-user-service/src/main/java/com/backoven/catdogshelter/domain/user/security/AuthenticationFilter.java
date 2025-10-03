package com.backoven.catdogshelter.domain.user.security;

import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestLoginDTO;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.LoginHistoryEntity;
import com.backoven.catdogshelter.domain.user.command.domain.repository.LoginHistoryRepository;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AuthenticationFilter
 * - 사용자가 로그인할 때 동작하는 커스텀 필터
 * - UsernamePasswordAuthenticationFilter를 확장
 * - 로그인 시도 → 인증 성공 시 JWT 토큰 생성 및 로그인 기록 저장
 */
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Environment env;                       // 환경설정 값(token.secret, expiration_time 등) 참조
    private final LoginHistoryRepository loginHistoryRepository; // 로그인 기록 저장용 Repository

    // AuthenticationManager, Environment, LoginHistoryRepository 주입
    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                Environment env,
                                LoginHistoryRepository loginHistoryRepository) {
        super(authenticationManager);
        this.env = env;
        this.loginHistoryRepository = loginHistoryRepository;
    }

    /**
     * 로그인 시도 메서드
     * - 클라이언트가 전달한 JSON(body)을 DTO로 변환
     * - UsernamePasswordAuthenticationToken을 생성해 AuthenticationManager로 전달
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // Request body(JSON) → RequestLoginDTO 변환
            RequestLoginDTO creds = new ObjectMapper()
                    .readValue(request.getInputStream(), RequestLoginDTO.class);

            // 아이디/비밀번호 기반 토큰 생성 후 AuthenticationManager로 인증 시도
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserAccount(), creds.getUserPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 인증 성공 메서드
     * - 인증 완료 후 실행
     * - JWT 토큰 생성 및 응답 헤더에 추가
     * - 로그인 기록 저장
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        log.info("로그인 성공 → Authentication 객체 반환: {}", authResult);

        // 인증된 사용자 정보 (principal) 추출
        String id = ((User) authResult.getPrincipal()).getUsername();
        log.info("회원 아이디: {}", id);

        // 권한 목록 추출
        List<String> roles = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        log.info("로그인한 회원 권한들: {}", roles);
        log.info("만료 시간: {}", env.getProperty("token.expiration_time"));

        // CustomUserDetails 캐스팅 후 추가 정보 추출
        CustomUserDetails customUser = (CustomUserDetails) authResult.getPrincipal();
        String username = customUser.getUsername();
        Integer userId = customUser.getUserId();

        // JWT Payload 설정
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", List.of("ROLE_USER")); // 권한
        claims.put("userId", userId); // 유저 ID

        // JWT 토큰 생성
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        // 토큰을 응답 헤더에 추가
        response.addHeader("token", token);

        // 로그인 기록 저장
        LoginHistoryEntity history = LoginHistoryEntity.builder()
                .ipAddress(request.getRemoteAddr())
                .loggedAt(LocalDateTime.now().toString())
                .userId(customUser.getUserId())
                .build();

        loginHistoryRepository.save(history);
        log.info("로그인 히스토리 저장 완료: {}", history);
    }
}
