package com.backoven.catdogshelter.domain.user.security;

import com.backoven.catdogshelter.domain.user.command.application.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final UserService userService;

    public JwtUtil(@Value("${token.secret}") String secretKey,
                   UserService userService) {
        // Base64 인코딩된 문자열을 HMAC SHA Key로 변환
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.userService = userService;
    }

    /**
     * 토큰 유효성 검증
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.warn("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.warn("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.warn("지원하지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.warn("JWT 클레임이 비어있습니다.");
        }
        return false;
    }

    /**
     * JWT에서 Authentication 객체 생성
     */
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 권한 문자열을 List<GrantedAuthority>로 변환
        Collection<SimpleGrantedAuthority> authorities =
                ((List<?>) claims.get("auth")).stream()
                        .map(Object::toString)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // DB에서 유저 정보 조회
        UserDetails userDetails = userService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    /**
     * 토큰에서 userId 추출
     */
    public Integer getUserId(String token) {
        return parseClaims(token).get("userId", Integer.class);
    }

    /**
     * 토큰에서 Claims(페이로드) 추출
     */
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims(); // 만료된 토큰도 Claims는 꺼낼 수 있음
        }
    }
}
