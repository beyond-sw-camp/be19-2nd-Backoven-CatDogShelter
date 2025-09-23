package com.backoven.catdogshelter.domain.post.query.controller;

import com.backoven.catdogshelter.domain.post.query.dto.PostDetailDTO;
import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import com.backoven.catdogshelter.domain.post.query.service.PostService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    public final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/post-posts")
    public List<PostInventoryDTO> getPostInventory() {
        return postService.getPostInventory();
    }

    @RequestMapping("/post/{id}")
    public PostDetailDTO getPostDetail(@PathVariable int id) {
        return postService.getPostDetail(id);
    }

}
