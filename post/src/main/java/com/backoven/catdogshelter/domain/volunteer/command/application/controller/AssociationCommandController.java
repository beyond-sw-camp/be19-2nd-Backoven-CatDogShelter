package com.backoven.catdogshelter.domain.volunteer.command.application.controller;

import com.backoven.catdogshelter.domain.volunteer.command.application.dto.ReqApplicationDTO;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.ReqAssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.command.application.service.ApplicationDetailsCommandService;
import com.backoven.catdogshelter.domain.volunteer.command.application.service.AssociationCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@Slf4j
@RestController
@RequestMapping("/volunteer-associations")
public class AssociationCommandController {
    private final AssociationCommandService associationCommandService;
    private final ApplicationDetailsCommandService applicationDetailsCommandService;

    @Autowired
    public AssociationCommandController(AssociationCommandService associationCommandService, ApplicationDetailsCommandService applicationDetailsCommandService) {
        this.associationCommandService = associationCommandService;
        this.applicationDetailsCommandService = applicationDetailsCommandService;
    }

    // 봉사모임 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<?> writeAssociation(@RequestBody ReqAssociationDTO newAssociation) {

        associationCommandService.writeAssociation(newAssociation);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("새로운 봉사모임 게시글 등록완료");
    }

    // 봉사모임 게시글 수정
    @PutMapping("/modify/{associationId}")
    public ResponseEntity<?> modifyAssociation(@RequestBody ReqAssociationDTO requestDTO,
                                               @PathVariable Long associationId) {
        associationCommandService.modifyAssociation(requestDTO, associationId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(associationId + "번 봉사모임 게시글 수정완료");
    }

    // 봉사모임 신청내역 추가
    @PostMapping("/{volunteerId}/applications")
    public ResponseEntity<?> addApplicationDetail(@PathVariable Long volunteerId,
                                                  @RequestBody ReqApplicationDTO requestDTO) {

        Long newAppId = applicationDetailsCommandService.addApplicationDetail(volunteerId, requestDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("새로운 신청내용 등록: " + newAppId);
    }

    // 봉사 모임 신청 승인
    @PatchMapping("/applications/{applicationId}/status")
    public ResponseEntity<?> updateApplicationDetail(@PathVariable Long applicationId,
                                                     @RequestBody ReqApplicationDTO requestDTO){

        applicationDetailsCommandService.updateApplicationDetail(applicationId, requestDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );
        return ResponseEntity.ok().headers(headers).body(applicationId + "번 신청 승인 완료");
    }
}
