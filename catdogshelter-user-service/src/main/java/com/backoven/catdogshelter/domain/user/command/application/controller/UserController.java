package com.backoven.catdogshelter.domain.user.command.application.controller;

import com.backoven.catdogshelter.domain.user.UserDtoToDtoMapper;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyPasswordUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestPasswordDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestRegistUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responsedto.ResponseFindLoginUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responsedto.ResponseModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responsedto.ResponseRegistUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.user.UserDTO;
import com.backoven.catdogshelter.domain.user.command.application.service.UserService;
import com.backoven.catdogshelter.domain.user.security.JwtUtil;
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
    @PutMapping("/mypage/{userId}/withdraw")
    public ResponseEntity<?> deleterUserByPassword(
            @PathVariable int userId,
            @RequestBody RequestPasswordDTO passwordDTO,
            @RequestHeader("Authorization") String bearerToken) {
        // 토큰에서 userId 추출
        String token = bearerToken.replace("Bearer ", "");
        Integer tokenUserId = jwtUtil.getUserId(token);
        // 본인만 접근 가능하도록 체크
        if (userId != tokenUserId) {
            throw new AccessDeniedException("본인의 마이페이지에만 접근 가능합니다.");
        }
        // 본인일 경우에만 수정 진행
        userService.deleterUserByPassword(userId, passwordDTO.getCurrentPwd());
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
