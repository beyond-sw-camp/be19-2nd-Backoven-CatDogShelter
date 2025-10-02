package com.backoven.catdogshelter.domain.user.query.controller;

import com.backoven.catdogshelter.domain.user.query.dto.LoginHistoryDTO;
import com.backoven.catdogshelter.domain.user.query.dto.UserQueryDTO;
import com.backoven.catdogshelter.domain.user.query.service.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/user/admin")
public class UserQueryController {

    private UserQueryService userQueryService;

    @Autowired
    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> selectAllUsers(){
        List<UserQueryDTO> users = userQueryService.selectAllUsers();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/general")
    public ResponseEntity<?> selectAllUsersByGeneral(){
        List<UserQueryDTO> usersByGeneral = userQueryService.selectAllUsersByGeneral();
        return ResponseEntity.ok().body(usersByGeneral);
    }
    @GetMapping("/black")
    public ResponseEntity<?> selectAllUsersByBlack(){
        List<UserQueryDTO> usersByBlack = userQueryService.selectAllUsersByBlack();
        return ResponseEntity.ok().body(usersByBlack);
    }
    @GetMapping("/canceled")
    public ResponseEntity<?> selectAllUsersByCanceled(){
        List<UserQueryDTO> usersByCanceled = userQueryService.selectAllUsersByCanceled();
        return ResponseEntity.ok().body(usersByCanceled);
    }
    @GetMapping("/login-history")
    public ResponseEntity<?> selectAllLoginHistory(){
        List<LoginHistoryDTO> usersLoginHistory = userQueryService.selectAllLoginHistory();
        return ResponseEntity.ok().body(usersLoginHistory);
    }
}
