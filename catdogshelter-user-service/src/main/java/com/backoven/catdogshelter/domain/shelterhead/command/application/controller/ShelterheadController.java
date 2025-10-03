package com.backoven.catdogshelter.domain.shelterhead.command.application.controller;

import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.requestdto.RequestRegistShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto.ResponseFindShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto.ResponseRegistShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.ShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.service.ShelterheadService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/shelter-head")
@Controller
public class ShelterheadController {

    private ShelterheadService shelterheadService;
    private ModelMapper modelMapper;

    public ShelterheadController(ShelterheadService shelterheadService,
                                 ModelMapper modelMapper) {
        this.shelterheadService = shelterheadService;
        this.modelMapper = modelMapper;
    }

    /* 로그인 기능 전 회원가입 기능 */
    @PostMapping("/regist")
    // 회원가입 할 때, 사용자의 요청은 RequestRegistUserDTO(뒷 부분)가 받고,
    // 다시 반환할 때는 ResponseRegistUserDTO(앞 부분)으로 반환한다.
    public ResponseEntity<ResponseRegistShelterheadDTO> registUser(@RequestBody RequestRegistShelterheadDTO newUser){
        // RequestRegistUserDTO로 만은 값을 UserDTO 형태로 변환시킴
        ShelterheadDTO userDTO = modelMapper.map(newUser, ShelterheadDTO.class);

        shelterheadService.registUser(userDTO);

        ResponseRegistShelterheadDTO responseUser = modelMapper.map(userDTO, ResponseRegistShelterheadDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/mypage/{memNo}")
    public ResponseEntity<ResponseFindShelterheadDTO> getUsers(@PathVariable String memNo){
        ShelterheadDTO shelterHeadDTO = shelterheadService.getShelter_headById(memNo);

        ResponseFindShelterheadDTO responseFindShelterHeadDTO =
                modelMapper.map(shelterHeadDTO, ResponseFindShelterheadDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseFindShelterHeadDTO);
    }
}


