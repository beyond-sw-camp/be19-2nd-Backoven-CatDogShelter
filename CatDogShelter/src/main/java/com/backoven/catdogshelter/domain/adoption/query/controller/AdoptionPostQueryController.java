package com.backoven.catdogshelter.domain.adoption.query.controller;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import com.backoven.catdogshelter.domain.adoption.query.service.AdoptionPostQueryServiceImpl;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;

@RestController
@RequestMapping("/adoption/post")
public class AdoptionPostQueryController {
    private final AdoptionPostQueryServiceImpl adoptionService;

    @Autowired
    public AdoptionPostQueryController(AdoptionPostQueryServiceImpl adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping("/board")
    public ResponseEntity<List<AdoptionPostAllQueryDTO>> findAllPosts(){
        List<AdoptionPostAllQueryDTO> adotpionPostList =
                adoptionService.selectAdoptionAllPosts();
        return ResponseEntity.ok(adotpionPostList);
    }
    @GetMapping("/{adoptionPostId}")
    public ResponseEntity<?> findPostById(@PathVariable int adoptionPostId){
        AdoptionPostDetailQueryDTO adoptionPostDetailDTO =
                adoptionService.selectAdoptionPostById(adoptionPostId);
        return ResponseEntity
                .created(URI.create("adoption/post/" + adoptionPostId)) //201
                .body(adoptionPostDetailDTO);
    }
    @GetMapping("/board/view")
    public ResponseEntity<?> findPostByView(){
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionAllPostsByView();
        return ResponseEntity.ok(adoptionPostList);
    }
    @GetMapping("/board/liked")
    public ResponseEntity<?> findPostByLiked(){
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionAllPostsByLiked();
        return ResponseEntity.ok(adoptionPostList);
    }
    // 키워드 검색 (title/content/breed 중 택1)
    @GetMapping("/search/keyword")
    public List<AdoptionPostAllQueryDTO> findPostByKeyword(SearchCriteria criteria) {
        return adoptionService.selectAdoptionPostByKeyword(criteria);
    }

    // 동물 조건 검색 (복수 조건 AND)
    @GetMapping("/search/condition")
    public List<AdoptionPostAllQueryDTO> findPostByAnimalCondition(SearchCriteria criteria) {
        return adoptionService.selectAdoptionPostByAnimalCondition(criteria);
    }

}
