package com.backoven.catdogshelter.domain.shelter_head.command.application.service;

import com.backoven.catdogshelter.domain.shelter_head.command.application.dto.Shelter_headDTO;

/* 인터페이스를 사용하므로써 느슨한 연결, 고립성 그러니까 결합도를 낮추려는 목적이다. */
/* Controller(사용자)가 내부의 기능(UserServiceImpl)를 보지 못하게 하기 위함이다. */
public interface Shelter_headService {
    void registUser(Shelter_headDTO userDTO);
}
