package com.backoven.catdogshelter.domain.shelter_head.command.application.controller;

import com.backoven.catdogshelter.domain.shelter_head.command.application.dto.RequestRegistShelter_headDTO;
import com.backoven.catdogshelter.domain.shelter_head.command.application.dto.ResponseRegistShelter_headDTO;
import com.backoven.catdogshelter.domain.shelter_head.command.application.dto.Shelter_headDTO;
import com.backoven.catdogshelter.domain.shelter_head.command.application.service.Shelter_headService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/post")
@Controller
public class Shelter_headExController {

    private Shelter_headService shelter_headService;
    private ModelMapper modelMapper;

    public Shelter_headExController(Shelter_headService shelter_headService,
                                    ModelMapper modelMapper) {
        this.shelter_headService = shelter_headService;
        this.modelMapper = modelMapper;
    }

    /* 로그인 기능 전 회원가입 기능 */
    @PostMapping("/users")
    // 회원가입 할 때, 사용자의 요청은 RequestRegistUserDTO(뒷 부분)가 받고,
    // 다시 반환할 때는 ResponseRegistUserDTO(앞 부분)으로 반환한다.
    public ResponseEntity<ResponseRegistShelter_headDTO> registUser(@RequestBody RequestRegistShelter_headDTO newUser){
        // RequestRegistUserDTO로 만은 값을 UserDTO 형태로 변환시킴
        Shelter_headDTO userDTO = modelMapper.map(newUser, Shelter_headDTO.class);

        shelter_headService.registUser(userDTO);

        ResponseRegistShelter_headDTO responseUser = modelMapper.map(userDTO, ResponseRegistShelter_headDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}


