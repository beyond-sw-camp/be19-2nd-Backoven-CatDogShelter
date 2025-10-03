package com.backoven.catdogshelter.domain.shelterhead.command.application.service;

import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.ShelterheadDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/* 인터페이스를 사용하므로써 느슨한 연결, 고립성 그러니까 결합도를 낮추려는 목적이다. */
/* Controller(사용자)가 내부의 기능(UserServiceImpl)를 보지 못하게 하기 위함이다. */

/* security 폴더에 JwtAuthenticationProvider 클래스에서 아이디를 입력 받으면 UserDetailsService(처음부터 설치되어 잇엇음)
   에 있는 loadUserByUsername을 호출한다. */
public interface ShelterheadService extends UserDetailsService {
    ShelterheadDTO getShelter_headById(String memNo);

    void registUser(ShelterheadDTO userDTO);
}
