package com.backoven.catdogshelter.domain.adoption.query.controller;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;

import com.backoven.catdogshelter.domain.adoption.query.service.AdoptionPostQueryServiceImpl;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostQueryDTO;

@RestController
@RequestMapping("/adoption/post")
public class AdoptionPostQueryController {
    private final AdoptionPostQueryServiceImpl adoptionService;

    @Autowired
    public AdoptionPostQueryController(AdoptionPostQueryServiceImpl adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping("/board")
    public ResponseEntity<List<AdoptionPostQueryDTO>> findAllPosts(){
        List<AdoptionPostQueryDTO> adotpionPostList =
                adoptionService.selectAdoptionAllPosts();
        return ResponseEntity.ok(adotpionPostList);
    }
    @GetMapping("/{adoptionPostId}")
    public ResponseEntity<?> findPostById(@PathVariable int adoptionPostId){
        AdoptionPostDetailDTO adoptionPostDetailDTO =
                adoptionService.selectAdoptionPostById(adoptionPostId);
        return ResponseEntity
                .created(URI.create("adoption/post/" + adoptionPostId)) //201
                .body(adoptionPostDetailDTO);
    }

    @GetMapping("/search/{condition}")
    public List<AdoptionPostQueryDTO> findPostByKeyword(
            @PathVariable String condition){
        List<AdoptionPostQueryDTO> adotpionPostList =
                adoptionService.selectAdoptionPostByKeyword(condition);
        return adotpionPostList;
    }

    @GetMapping("/search/{animalCondition}")
    public List<AdoptionPostQueryDTO> findPostByAnimalCondition(
            @PathVariable Map<String, String> animalCondition){
        List<AdoptionPostQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionPostByAnimalCondition(animalCondition);
        return adoptionPostList;
    }

}
