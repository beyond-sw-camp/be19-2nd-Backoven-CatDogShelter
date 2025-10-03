package com.backoven.catdogshelter.domain.shelterhead.security;

import com.backoven.catdogshelter.domain.shelterhead.command.application.service.ShelterheadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * ShelterHead 로그인 전용 AuthenticationProvider
 * - AuthenticationFilter → UsernamePasswordAuthenticationToken 전달
 * - DB 조회(ShelterheadService) 후 비밀번호 검증
 * - 검증 성공 시 인증된 Authentication 반환
 */
@Slf4j
@Component
public class ShelterheadJwtAuthenticationProvider implements AuthenticationProvider {

    private final ShelterheadService shelterheadService;  // DB에서 shelter_head 회원 정보 조회
    private final PasswordEncoder passwordEncoder;        // 입력한 비밀번호와 암호화된 비밀번호 비교

    public ShelterheadJwtAuthenticationProvider(ShelterheadService shelterheadService,
                                                PasswordEncoder passwordEncoder) {
        this.shelterheadService = shelterheadService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 인증 처리 메서드
     * - AuthenticationFilter에서 넘어온 UsernamePasswordAuthenticationToken을 기반으로 동작
     * - 입력한 계정/비밀번호 검증
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 사용자가 입력한 계정 (username 역할)
        String headAccount = authentication.getName();

        // 사용자가 입력한 비밀번호 (Object → String 변환)
        String pwd = authentication.getCredentials().toString();

        // DB에서 계정 정보 조회 (UserDetails 구현체 반환)
        UserDetails userDetails = shelterheadService.loadUserByUsername(headAccount);

        // 비밀번호 검증 (평문 vs 암호화된 비밀번호)
        if (!passwordEncoder.matches(pwd, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        /**
         * 검증 성공 → 인증된 Authentication 객체 생성
         * - 첫 번째 매개변수: 인증된 사용자(UserDetails)
         * - 두 번째 매개변수: 비밀번호는 보안상 null 처리
         * - 세 번째 매개변수: 사용자 권한 목록
         */
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    /**
     * 이 Provider가 어떤 Authentication 타입을 처리할지 명시
     * - UsernamePasswordAuthenticationToken 타입만 처리 가능
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
