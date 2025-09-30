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

/* 설명. Jwt 토큰 방식의 로그인을 구현할 때
*       UsernamePasswordAuthenticationToken을 처리 할 프로바이더 */
/* 설명. Service 계층의 UserDetailService를 활용해 DB에서 사용자 조회 후 인증 */
@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    /* 설명. 평문과 암호화 된 다이제스트를 비교하기 위한 도구 */
    private final PasswordEncoder passwordEncoder; // 비교해주는 시큐리티라이브러리

    @Autowired
    public JwtAuthenticationProvider(UserService userService,
                                     PasswordEncoder passwordEnconder) {
        this.userService = userService;
        this.passwordEncoder = passwordEnconder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        /* 설명. 사용자가 로그인 시 입력한 값 */
        String userAccount = authentication.getName();
        // getCredentials() 반환타입이 object라 다운캐스팅
//        String pwd = (String)authentication.getCredentials();
        String pwd = authentication.getCredentials().toString();
        // db다녀옴
        /* 설명. DB에 있는 해당 회원의 정보 */
        // provider는 둘을 비교하면 됌(단방향 암호화라 라이브러리)
        UserDetails userDetails = userService.loadUserByUsername(userAccount);

        if(!passwordEncoder.matches(pwd,userDetails.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        /* 설명. 예외가 발생하지 않고 이 부분 이후가 실행되면
        *       UserDetails에 담긴(인증된 회원정보) 정보로
        *       Token을 만듬*/
        return new UsernamePasswordAuthenticationToken(
                userDetails,     // 여기 안에 암호화된 비번은 [Encrypted]로 가려짐
                null, // 암호화된 패스워드를 로그인에 성공하면 가려줘야 함
                userDetails.getAuthorities()
        );
    }

    // 토큰이 여기로 넘어오게 됨
    // 어떤걸 처리할 수 있다
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
