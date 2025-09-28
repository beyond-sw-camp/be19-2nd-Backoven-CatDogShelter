package com.backoven.catdogshelter.domain.adoption.query.controller;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.backoven.catdogshelter.domain.adoption.query.service.AdoptionPostQueryServiceImpl;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;

@RestController
@RequestMapping("catdogshelter/adoption-post")
public class AdoptionPostQueryController {
    private final AdoptionPostQueryServiceImpl adoptionService;

    @Autowired
    public AdoptionPostQueryController(AdoptionPostQueryServiceImpl adoptionService) {
        this.adoptionService = adoptionService;
    }
    // 게시판 목록 조회
    @GetMapping("/board")
    public ResponseEntity<List<AdoptionPostAllQueryDTO>> findAllPosts(){
        List<AdoptionPostAllQueryDTO> adotpionPostList =
                adoptionService.selectAdoptionAllPosts();
        return ResponseEntity.ok(adotpionPostList);
    }
    // 게시글 조회
    @GetMapping("/{adoptionPostId}")
    public ResponseEntity<?> findPostById(@PathVariable int adoptionPostId){
        AdoptionPostDetailQueryDTO adoptionPostDetailDTO =
                adoptionService.selectAdoptionPostById(adoptionPostId);
        return ResponseEntity
                .created(URI.create("adoption/post/" + adoptionPostId))
                .body(adoptionPostDetailDTO);
    }
    // 게시글 안에 파일조회
    @GetMapping("/{adoptionPostId}/image/{filename}")
    public ResponseEntity<Resource> findPostImageById(@PathVariable String filename)
            throws MalformedURLException {
        Path path = Paths.get("/Users/haeone/Desktop/be19-2nd-backoven-petShelter/UploadFiles/"
                + filename);
        Resource resource = new UrlResource(path.toUri());
        if(!resource.exists()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
    // 조회순 게시판 목록 조회
    @GetMapping("/board/view")
    public ResponseEntity<?> findPostByView(){
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionAllPostsByView();
        return ResponseEntity.ok(adoptionPostList);
    }
    // 추천순 게시판 목록 조회
    @GetMapping("/board/liked")
    public ResponseEntity<?> findPostByLiked(){
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionAllPostsByLiked();
        return ResponseEntity.ok(adoptionPostList);
    }
    // 키워드 검색 (title/content/breed 중 택1)
    @GetMapping("/search/keyword")
    public ResponseEntity<?> findPostByKeyword(SearchCriteria criteria) {
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionPostByKeyword(criteria);
        return ResponseEntity.ok(adoptionPostList);
    }
    // 동물 조건 검색 (복수 조건 AND)
    @GetMapping("/search/condition")
    public ResponseEntity<?> findPostByAnimalCondition(SearchCriteria criteria) {
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionPostByAnimalCondition(criteria);
        return ResponseEntity.ok(adoptionPostList);
    }
}
