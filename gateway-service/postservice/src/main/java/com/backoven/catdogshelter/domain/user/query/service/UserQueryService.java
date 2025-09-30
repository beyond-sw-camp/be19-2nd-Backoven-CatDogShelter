package com.backoven.catdogshelter.domain.user.query.service;

import com.backoven.catdogshelter.domain.user.query.dto.LoginHistoryDTO;
import com.backoven.catdogshelter.domain.user.query.dto.UserQueryDTO;
import com.backoven.catdogshelter.domain.user.query.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryService {
    private final UserMapper userMapper;

    @Autowired
    public UserQueryService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserQueryDTO> selectAllUsers() {
        List<UserQueryDTO> users = userMapper.selectAllUsers();
        return users;
    }

    public List<UserQueryDTO> selectAllUsersByGeneral() {
        List<UserQueryDTO> usersByGeneral = userMapper.selectAllUsersByGeneral();
        return usersByGeneral;
    }

    public List<UserQueryDTO> selectAllUsersByBlack() {
        List<UserQueryDTO> usersByBlack = userMapper.selectAllUsersByBlack();
        return usersByBlack;
    }

    public List<UserQueryDTO> selectAllUsersByCanceled() {
        List<UserQueryDTO> usersByCanceled = userMapper.selectAllUsersByCanceled();
        return usersByCanceled;
    }

    public List<LoginHistoryDTO> selectAllLoginHistory() {
        List<LoginHistoryDTO> usersLoginHistory = userMapper.selectAllLoginHistory();
        return usersLoginHistory;
    }
}
