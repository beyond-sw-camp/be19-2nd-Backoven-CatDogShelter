package com.backoven.catdogshelter.domain.shelterhead.security;

import com.backoven.catdogshelter.domain.shelterhead.command.application.service.ShelterheadService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Shelterhead 전용 JWT 유틸리티 클래스
 * - 토큰 생성 시 사용한 key를 기반으로 유효성 검증 및 Authentication 반환 처리
 */
@Slf4j
@Component
public class ShelterheadJwtUtil {

    private final Key key;                          // JWT 서명 검증용 키
    private final ShelterheadService shelterheadService; // DB에서 사용자 정보 조회하는 서비스

    /**
     * 생성자
     * - application.yml의 token.secret 값을 Base64 디코딩하여 Key 객체 생성
     */
    public ShelterheadJwtUtil(@Value("${token.secret}") String key,
                              ShelterheadService shelterheadService) {
        byte[] keyBytes = Decoders.BASE64.decode(key);       // String → byte[]
        this.key = Keys.hmacShaKeyFor(keyBytes);             // byte[] → Key 객체 변환
        this.shelterheadService = shelterheadService;
    }

    /**
     * JWT 토큰 유효성 검증
     * - 서명 검증 및 파싱
     * - 예외 발생 시 false 반환
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);   // 파싱 성공 → 유효한 토큰
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("유효하지 않은 JWT Token (서명 불일치 또는 형식 오류)");
        } catch (ExpiredJwtException e) {
            log.info("JWT 토큰 만료");
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 JWT Token");
        } catch (IllegalArgumentException e) {
            log.info("JWT 클레임이 비어 있음");
        }
        return false;
    }

    /**
     * JWT 토큰에서 Authentication 객체 반환
     * - 토큰 payload → Claims 파싱
     * - subject(아이디)로 DB 조회 후 UserDetails 생성
     * - 토큰에 저장된 권한(auth) → List<GrantedAuthority> 변환
     */
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        // DB에서 사용자 조회
        UserDetails userDetails = shelterheadService.loadUserByUsername(claims.getSubject());

        // 토큰에 권한이 없으면 예외 발생
        Collection<GrantedAuthority> authorities;
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한이 들어있지 않은 토큰입니다.");
        } else {
            authorities =
                    Arrays.stream(claims.get("auth").toString()
                                    .replace("[", "")
                                    .replace("]", "")
                                    .split(", "))
                            .map(SimpleGrantedAuthority::new)   // 권한 문자열 → GrantedAuthority 객체 변환
                            .collect(Collectors.toList());
        }

        // 인증 객체(Authentication) 생성 후 반환
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    /**
     * JWT 토큰에서 Claims(페이로드) 추출
     */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
