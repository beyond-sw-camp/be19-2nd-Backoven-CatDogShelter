package com.backoven.catdogshelter.domain.user.command.application.controller;

import com.backoven.catdogshelter.domain.user.UserMapper;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestlogin.RequestModifyPasswordUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestlogin.RequestModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestlogin.RequestRegistUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responselogin.ResponseFindLoginUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responselogin.ResponseModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responselogin.ResponseRegistUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.user.UserDTO;
import com.backoven.catdogshelter.domain.user.command.application.service.UserService;
import com.backoven.catdogshelter.domain.user.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@RestController()
@RequestMapping("/catdogshelter")
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
    // 유저 회원가입
    @PostMapping("/users")
    public ResponseEntity<ResponseRegistUserDTO> registUser(
            @RequestBody RequestRegistUserDTO newUser){
        UserDTO userDTO = UserMapper.toUserDTO(newUser);
        userService.registUser(userDTO);

        ResponseRegistUserDTO reponseUser =
                modelMapper.map(userDTO, ResponseRegistUserDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reponseUser);
    }
    // 로그인 마이페이지
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
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseUser);
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

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseUser);
    }

    // 마이페이지에서 비밀번호 수정
    @PutMapping("/user/{userId}/pwd")
    public ResponseEntity<Void> modifyUserPassword(
            @PathVariable int userId,
            @RequestBody RequestModifyPasswordUserDTO updatedUser,
            @RequestHeader("Authorization") String bearerToken) {

        // 토큰에서 userId 추출
        String token = bearerToken.replace("Bearer ", "");
        Integer tokenUserId = jwtUtil.getUserId(token);

        // 본인만 접근 가능하도록 체크
        if (userId != tokenUserId) {
            throw new AccessDeniedException("본인의 비밀번호만 수정 가능합니다.");
        }

        // 본인일 경우에만 수정 진행
        userService.modifyUserPassword(userId, updatedUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
