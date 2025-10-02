package com.backoven.catdogshelter.domain.shelterhead.security;

import com.backoven.catdogshelter.domain.shelterhead.command.application.service.Shelter_headService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/* Service 계층의 Shelter_headService를 활용해 DB에서 사용자 조회 후 인증 */
@Slf4j
@Component
public class Shelter_headJwtAuthenticationProvider implements AuthenticationProvider {

    private final Shelter_headService shelter_headService;
    private final PasswordEncoder passwordEncoder;      // 평문과 암호화 된 다이제스트를 비교하기 위한 도구

    public Shelter_headJwtAuthenticationProvider(Shelter_headService shelter_headService, PasswordEncoder passwordEncoder) {
        this.shelter_headService = shelter_headService;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    // 아래(supports)에서 지정한 토큰을 처리할 수 있다고 선언한 메서드
    // authentication에는 AuthenticationFilter 클래스에서 선언된 RequestLoginDTO가 넘어온다.
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String headAccount = authentication.getName();
        // getCredentail이 Object형 밖에 받지 못해 toString()으로 다운 캐스팅 하였다.
        String pwd = authentication.getCredentials().toString();

        // shelter_headService 거친 후에 DB에 있는 해당 회원의 아이디 가져오기
        UserDetails userDetails = shelter_headService.loadUserByUsername(headAccount);

        if(passwordEncoder.matches(pwd, userDetails.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        /*  예외가 발생하지 않고 이 부분 이후가 실행되면 UserDetails에 담긴(인증된 회원정보) 정보로 Token을 만듦 */
        // 두 번째 매개변수는 원래 비번인데 로그인 이후 비밀번호를 가리기 위해서 null로 표시
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
