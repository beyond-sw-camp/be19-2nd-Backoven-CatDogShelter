//package com.backoven.catdogshelter.domain.adoption.command.application.controller;
//
//import com.backoven.catdogshelter.domain.adoption.command.application.dto.AdoptionPostCommandDTO;
//import com.backoven.catdogshelter.domain.adoption.command.application.response.ResponseMessage;
//import com.backoven.catdogshelter.domain.adoption.command.application.service.AdoptionPostCommandService;
//import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPostEntity;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.nio.charset.Charset;
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequestMapping("/adoption/post")
//public class AdoptionPostCommandController {
//
//    private final AdoptionPostCommandService adoptionPostCommandService;
//    private List<AdoptionPostCommandDTO> adoptionPosts;
//
//    @Autowired
//    public AdoptionPostCommandController(
//            AdoptionPostCommandService adoptionPostCommandService) {
//        this.adoptionPostCommandService = adoptionPostCommandService;
//    }
//
//    @PostMapping("/regist")
//    public ResponseEntity<?> registAdoptionPost(
//            @RequestBody AdoptionPostCommandDTO newPost){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(
//                new MediaType("application", "json",
//                        Charset.forName("UTF-8")));
//
//        adoptionPostCommandService.registAdoptionPost(newPost);
//        return ResponseEntity
//                .created(URI.create("adoption/post/board")) //201
//                .build();
//    }
//
//    @PutMapping("/{postId}")
//    public ResponseEntity<?> modifyAdoptionPost(
//            @RequestBody AdoptionPostCommandDTO modifyPost,
//            @PathVariable int postId){
//        adoptionPostCommandService.modifyAdoptionPost(modifyPost);
//
//        return ResponseEntity
//                .created(URI.create("adoption/post/"+ postId)) //201
//                .body(modifyPost);
//    }
//
//    @PutMapping("/{postId}/blind")
//    public ResponseEntity<?> removeAdoptionPost(@PathVariable int postId){
//        adoptionPostCommandService.deleteAdoptionPost(postId);
//        return ResponseEntity
//                .created(URI.create("adoption/post/"+ postId)).build(); //201
//    }
//
//}
