package com.backoven.catdogshelter.domain.volunteer.query.controller;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostListDTO;
import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerPostQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 봉사후기 게시글 목록 조회
    // (번호, 제목, 작성일, 작성자, 조회수, 추천수)
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
    public ResponseEntity<VolunteerPostDTO> getVolunteerPost(@PathVariable int id) {

        VolunteerPostDTO post = volunteerPostQueryService.getVolunteerPost(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(post);
    }

    // 제목, 내용, 작성자에 검색어를 포함한 목록을 조회
    @GetMapping("/search")
    public ResponseEntity<List<VolunteerPostListDTO>> selectVolunteerPostsListByKeyword(@RequestParam(required = false) String keyword) {

        List<VolunteerPostListDTO> postList = volunteerPostQueryService.selectVolunteerPostsListByKeyword(keyword);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(postList);
    }

    // 봉사후기 게시글 조회수순 목록 조회
    // (번호, 제목, 작성일, 작성자, 조회수, 추천수)
    @GetMapping("/popular/view")
    public ResponseEntity<List<VolunteerPostListDTO>> selectVolunteerPostsByView() {

        List<VolunteerPostListDTO> postList = volunteerPostQueryService.selectVolunteerPostsByView();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(postList);
    }

    // 봉사후기 게시글 추천수순 목록 조회
    // (번호, 제목, 작성일, 작성자, 조회수, 추천수)
    @GetMapping("/popular/like")
    public ResponseEntity<List<VolunteerPostListDTO>> selectVolunteerPostsByLiked() {

        List<VolunteerPostListDTO> postList = volunteerPostQueryService.selectVolunteerPostsByLiked();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(postList);
    }

}
