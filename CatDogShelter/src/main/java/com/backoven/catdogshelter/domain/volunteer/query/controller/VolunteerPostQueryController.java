package com.backoven.catdogshelter.domain.volunteer.query.controller;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostListDTO;
import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerPostQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/volunteer-posts")
public class VolunteerPostQueryController {

    private final VolunteerPostQueryService volunteerPostQueryService;

    @Autowired
    public VolunteerPostQueryController(VolunteerPostQueryService volunteerPostQueryService) {
        this.volunteerPostQueryService = volunteerPostQueryService;
    }

    // 봉사후기 게시글 목록 조회(번호, 제목, 작성일, 작성자, 조회수, 추천수)
    @GetMapping("/volunteerpostslist")
    public ResponseEntity<List<VolunteerPostListDTO>> getVolunteerPostsList() {

        List<VolunteerPostListDTO> postList = volunteerPostQueryService.getVolunteerPostList();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(postList);
    }

    // 봉사활동 후기 게시글 상세 조회
    // (번호, 제목, 내용, 작성자, 등급, 파일, 댓글, 작성일, 수정일, 조회수, 추천수)
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerPostDTO> getVolunteerPost(int id) {

        VolunteerPostDTO post = volunteerPostQueryService.getVolunteerPost(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(post);
    }

}
