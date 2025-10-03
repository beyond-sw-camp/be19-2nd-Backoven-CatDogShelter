package com.backoven.catdogshelter.domain.user.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * CustomUserDetails
 * - Spring Security가 기본 제공하는 User 클래스를 상속받아 확장한 클래스
 * - username, password, 권한(authorities) 외에 userId 같은 추가 정보를 담기 위함
 * - 여기서는 로그인 이력에 삽입하기 위해서 userId 추가정보 담음
 */
public class CustomUserDetails extends User {

    private final Integer userId; // DB의 user_id 값 (추가 필드)

    // 생성자
    // - User 클래스의 생성자를 호출하면서 username, password, 권한들을 넘겨줌
    // - 추가로 userId를 저장
    public CustomUserDetails(Integer userId, String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }

    // userId Getter
    // - 토큰 생성 시 payload에 담거나 로그인 이력 저장 시 사용 가능
    public Integer getUserId() {
        return userId;
    }
}
