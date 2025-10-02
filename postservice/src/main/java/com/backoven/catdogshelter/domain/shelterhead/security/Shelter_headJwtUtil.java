package com.backoven.catdogshelter.domain.shelterhead.security;

import com.backoven.catdogshelter.domain.shelterhead.command.application.service.Shelter_headService;
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

@Slf4j
@Component
public class Shelter_headJwtUtil {

    private final Key key;
    private final Shelter_headService shelter_headService;

    public Shelter_headJwtUtil(@Value("${token.secret}")String key,
                               Shelter_headService shelter_headService) {
        /* 설명. String -> Key로 변환 */
        byte[] keyBytes = Decoders.BASE64.decode(key);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.shelter_headService = shelter_headService;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            return true;
        } catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("유효하지 않은  JWT Token(아무런 값이 없음");
        } catch(ExpiredJwtException e){
            log.info("만료시간이 지남");
        } catch(UnsupportedJwtException e){
            log.info("지원하지 않는 JWT Token(지원하지 않는 양식)");
        } catch(IllegalArgumentException e){
            log.info("토큰의 클레임이 비어있음");
        }
        return false;
    }

    /* 유효성 검증이 된 토큰에서 인증 객체(Authentication)를 반환 */
    public Authentication getAuthentication(String token) {

        Claims claims = parseClaims(token);

        /* 토큰에 들어있던 이메일(sub)로 DB에서 회원 조회하고 UserDetails로 가져옴 */
        UserDetails userDetails = shelter_headService.loadUserByUsername(claims.getSubject());

        /* 토큰에 들어있는 권한들을 Lsit<GrantedAuthority>로 꺼내보기 */
        /* 토큰에 권한이 들어 있을 때 */
        Collection<GrantedAuthority> authorities = null;
        if(claims.get("auth")==null){
            throw new RuntimeException("권한이 들어있지 않은 토큰입니다.");
        } else{
            authorities =
                    Arrays.stream(claims.get("auth").toString()
                            .replace("[","")
                            .replace("]","")
                            .split(", "))
                            .map(role -> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toList());
        }

        /* Service 계층의 loadUserByUsername() 메소드 반환값(UserDetails)를 활용할 때 */
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    /* 문자열 토른에서 payload에 담긴 클레임들만 추출 */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
