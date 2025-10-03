package com.backoven.catdogshelter.domain.user.security;

import com.backoven.catdogshelter.domain.user.command.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * JWT 인증용 AuthenticationProvider
 * - 로그인 시 UsernamePasswordAuthenticationToken을 처리
 * - UserDetailsService(UserService)를 통해 DB에서 사용자 조회 후 검증
 */
@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // 비밀번호 검증용

    @Autowired
    public JwtAuthenticationProvider(UserService userService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 인증 로직
     * - 사용자가 입력한 이메일/비밀번호를 확인
     * - DB에서 조회한 UserDetails와 비교
     * - 비밀번호 일치 시 Authentication 객체 반환
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 사용자가 로그인 시 입력한 값
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        // DB에서 사용자 정보 조회
        UserDetails userDetails = userService.loadUserByUsername(email);

        // 비밀번호 검증
        if (!passwordEncoder.matches(pwd, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        // 인증 성공 시 UsernamePasswordAuthenticationToken 반환
        return new UsernamePasswordAuthenticationToken(
                userDetails,           // 인증된 사용자 정보
                null,                  // 비밀번호는 null로 처리 (보안상 숨김)
                userDetails.getAuthorities() // 사용자 권한
        );
    }

    /**
     * 지원 여부 확인
     * - 현재 Provider가 UsernamePasswordAuthenticationToken을 처리할 수 있는지 여부 반환
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
