package com.backoven.catdogshelter.domain.user.query.controller;

import com.backoven.catdogshelter.domain.user.query.dto.LoginHistoryDTO;
import com.backoven.catdogshelter.domain.user.query.dto.UserQueryDTO;
import com.backoven.catdogshelter.domain.user.query.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/catdogshelter/admin")
public class UserQueryController {

    private UserQueryService userQueryService;

    @Autowired
    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "전체 회원 조회", description = "모든 회원 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserQueryDTO.class))))
    @GetMapping("/user/all")
    public ResponseEntity<?> selectAllUsers(){
        List<UserQueryDTO> users = userQueryService.selectAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "일반 회원 조회", description = "일반 상태의 회원만 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserQueryDTO.class))))
    @GetMapping("/user/general")
    public ResponseEntity<?> selectAllUsersByGeneral(){
        List<UserQueryDTO> usersByGeneral = userQueryService.selectAllUsersByGeneral();
        return ResponseEntity.ok().body(usersByGeneral);
    }

    @Operation(summary = "블랙리스트 회원 조회", description = "블랙 처리된 회원만 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserQueryDTO.class))))
    @GetMapping("/user/black")
    public ResponseEntity<?> selectAllUsersByBlack(){
        List<UserQueryDTO> usersByBlack = userQueryService.selectAllUsersByBlack();
        return ResponseEntity.ok().body(usersByBlack);
    }
    @Operation(summary = "탈퇴 회원 조회", description = "탈퇴(해지)된 회원만 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserQueryDTO.class))))
    @GetMapping("/user/canceled")
    public ResponseEntity<?> selectAllUsersByCanceled(){
        List<UserQueryDTO> usersByCanceled = userQueryService.selectAllUsersByCanceled();
        return ResponseEntity.ok().body(usersByCanceled);
    }
    @Operation(summary = "회원 로그인 이력 조회", description = "모든 회원의 로그인 이력을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LoginHistoryDTO.class)))),
            @ApiResponse(responseCode = "404", description = "이력이 존재하지 않음")})
    @GetMapping("/user/login-history")
    public ResponseEntity<?> selectAllLoginHistory(){
        List<LoginHistoryDTO> usersLoginHistory = userQueryService.selectAllLoginHistory();
        return ResponseEntity.ok().body(usersLoginHistory);
    }
}
