package com.backoven.catdogshelter.domain.volunteer.query.controller;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationsListDTO;
import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerAssociationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/volunteerassociation-posts")
public class VolunteerAssociationQueryController {

    private final VolunteerAssociationQueryService volunteerAssociationQueryService;

    @Autowired
    public VolunteerAssociationQueryController(VolunteerAssociationQueryService volunteerAssociationQueryService) {
        this.volunteerAssociationQueryService = volunteerAssociationQueryService;
    }

    // 봉사모임 봉사활동 공고 게시글 목록 조회(번호, 제목, 작성일, 시작일, 상세위치, 작성자 )
    @GetMapping("/volunteerassociationslist")
    public ResponseEntity<List<VolunteerAssociationsListDTO>> getVolunteerAssociationsList() {

        List<VolunteerAssociationsListDTO> assocList = volunteerAssociationQueryService.getVolunteerAssociationList();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(assocList);
    }

    // 봉사활동 공고 게시글 상세 조회
    // (번호, 제목, 내용, 작성일, 봉사시간, 상세위치, 모집마감여부, 봉사종료여부, 모집인원, 시작일, 작성자)
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerAssociationDTO> getVolunteerAssociation(@PathVariable int id) {

        VolunteerAssociationDTO post = volunteerAssociationQueryService.getVolunteerAssociation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(post);
    }

    // 검색어 목록조회
    @GetMapping("/search")
    public ResponseEntity<List<VolunteerAssociationsListDTO>> selectVolunteerAssociationsByKeyword(@RequestParam(required = false) String keyword) {
        List<VolunteerAssociationsListDTO> posts = volunteerAssociationQueryService.selectVolunteerAssociationsByKeyword(keyword);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(posts);
    }

    // 봉사신청내역 목록

    
    
}
