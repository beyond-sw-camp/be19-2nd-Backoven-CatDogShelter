package com.backoven.catdogshelter.domain.user.command.application.controller;

import com.backoven.catdogshelter.domain.user.UserDtoToDtoMapper;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestlogin.RequestModifyPasswordUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestlogin.RequestModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestlogin.RequestRegistUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responselogin.ResponseFindLoginUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responselogin.ResponseModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responselogin.ResponseRegistUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.user.UserDTO;
import com.backoven.catdogshelter.domain.user.command.application.service.UserService;
import com.backoven.catdogshelter.domain.user.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
@RequestMapping("/user")
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(ModelMapper modelMapper,
                          UserService userService,
                          JwtUtil jwtUtil) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    @Operation(summary = "일반회원 회원가입", description = "새로운 일반 회원을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = ResponseRegistUserDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content),
            @ApiResponse(responseCode = "409", description = "중복 아이디/이메일", content = @Content)})
    // 일반회원 회원가입
    @PostMapping("/regist")
    public ResponseEntity<ResponseRegistUserDTO> registUser(
            @RequestBody RequestRegistUserDTO newUser){
        UserDTO userDTO = UserDtoToDtoMapper.toUserDTO(newUser);
        userService.registUser(userDTO);

        ResponseRegistUserDTO reponseUser =
                modelMapper.map(userDTO, ResponseRegistUserDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reponseUser);
    }

    // 마이페이지
    @Operation(summary = "마이페이지 조회", description = "특정 사용자의 마이페이지 정보를 조회합니다. (본인만 접근 가능)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseFindLoginUserDTO.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content)})
    @GetMapping("/mypage/{userId}")
    public ResponseEntity<ResponseFindLoginUserDTO> getUsers(
            @PathVariable Integer userId,
            @RequestHeader("Authorization") String bearerToken){
        // 토큰 확인
        String token = bearerToken.replace("Bearer ", "");
        Integer tokenUserId = jwtUtil.getUserId(token);

        if (!userId.equals(tokenUserId)) {
            throw new AccessDeniedException("본인의 마이페이지에만 접근 가능합니다.");
        }
        UserDTO userDTO = userService.getUserById(userId);
        ResponseFindLoginUserDTO responseUser =
                modelMapper.map(userDTO, ResponseFindLoginUserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    // 마이페이지에서 내정보 수정
    @Operation(summary = "마이페이지 정보 수정", description = "본인의 마이페이지 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(schema = @Schema(implementation = ResponseModifyUserDTO.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content)})
    @PutMapping("/mypage/{userId}")
    public ResponseEntity<ResponseModifyUserDTO> modifyUser(
            @PathVariable int userId,
            @RequestBody RequestModifyUserDTO updatedUser,
            @RequestHeader("Authorization") String bearerToken) {

        // 토큰에서 userId 추출
        String token = bearerToken.replace("Bearer ", "");
        Integer tokenUserId = jwtUtil.getUserId(token);

        // 본인만 접근 가능하도록 체크
        if (userId != tokenUserId) {
            throw new AccessDeniedException("본인의 마이페이지에만 접근 가능합니다.");
        }
        // 본인일 경우에만 수정 진행
        UserDTO userDTO = userService.modifyUser(userId, updatedUser);
        ResponseModifyUserDTO responseUser =
                modelMapper.map(userDTO, ResponseModifyUserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    // 마이페이지에서 비밀번호 수정
    @Operation(summary = "비밀번호 수정", description = "마이페이지에서 본인의 비밀번호를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content),
            @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content)})
    @PutMapping("/mypage/{userId}/pwd")
    public ResponseEntity<?> modifyUserPassword(
            @PathVariable int userId,
            @RequestBody RequestModifyPasswordUserDTO updatedUser,
            @RequestHeader("Authorization") String bearerToken) {
        // 토큰에서 userId 추출
        String token = bearerToken.replace("Bearer ", "");
        Integer tokenUserId = jwtUtil.getUserId(token);
        // 본인만 접근 가능하도록 체크
        if (userId != tokenUserId) {
            throw new AccessDeniedException("본인의 마이페이지에만 접근 가능합니다.");
        }
        // 본인일 경우에만 수정 진행
        userService.modifyUserPassword(userId, updatedUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 마이페이지 탈퇴
    @Operation(summary = "회원 탈퇴", description = "마이페이지에서 본인의 계정을 탈퇴(삭제)합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "탈퇴 성공", content = @Content),
            @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content)})
    @GetMapping("/mypage/{userId}/withdraw")
    public ResponseEntity<?> deleterUserByPassword(
            @PathVariable int userId,
            @RequestBody RequestModifyPasswordUserDTO updatedUser,
            @RequestHeader("Authorization") String bearerToken) {
        // 토큰에서 userId 추출
        String token = bearerToken.replace("Bearer ", "");
        Integer tokenUserId = jwtUtil.getUserId(token);
        // 본인만 접근 가능하도록 체크
        if (userId != tokenUserId) {
            throw new AccessDeniedException("본인의 마이페이지에만 접근 가능합니다.");
        }
        // 본인일 경우에만 수정 진행
        userService.deleterUserByPassword(userId, updatedUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
