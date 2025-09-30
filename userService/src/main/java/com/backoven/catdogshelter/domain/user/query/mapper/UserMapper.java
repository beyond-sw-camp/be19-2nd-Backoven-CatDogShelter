package com.backoven.catdogshelter.domain.user.query.mapper;

import com.backoven.catdogshelter.domain.user.query.dto.LoginHistoryDTO;
import com.backoven.catdogshelter.domain.user.query.dto.UserQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 전체 회원 조회
    List<UserQueryDTO> selectAllUsers();
    // 일반 회원 조회
    List<UserQueryDTO> selectAllUsersByGeneral();
    // 블랙 회원 조회
    List<UserQueryDTO> selectAllUsersByBlack();
    // 탈퇴 회원 조회
    List<UserQueryDTO> selectAllUsersByCanceled();
    // 로그인 이력 조회
    List<LoginHistoryDTO> selectAllLoginHistory();
}
