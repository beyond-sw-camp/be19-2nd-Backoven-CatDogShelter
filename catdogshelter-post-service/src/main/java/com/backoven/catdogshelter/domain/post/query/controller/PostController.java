package com.backoven.catdogshelter.domain.post.query.controller;

import com.backoven.catdogshelter.domain.post.query.dto.*;
import com.backoven.catdogshelter.domain.post.query.service.PostService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    public final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/posts")
    public List<PostInventoryDTO> selectPostInventory() {
        return postService.selectPostInventory();
    }

    @RequestMapping("/{id}")
    public PostDetailDTO selectPostDetail(@PathVariable int id) {
        return postService.selectPostDetail(id);
    }

    @RequestMapping("/viewDesc")
    public List<PostInventoryDTO> viewDescPostInventory(){
        return postService.viewDescPostInventory();
    }

    @RequestMapping("/createdAtDesc")
    public List<PostInventoryDTO> createdAtPostInventory(){
        return postService.createdAtPostInventory();
    }

    @RequestMapping("/likedDesc")
    public List<PostLikedDescDTO> likedPostInventory(){
        return postService.likedPostInventory();
    }

    @RequestMapping("/report/{postId}")
    public  List<PostReportDetailDTO>  reportPost(@PathVariable int postId) {
        return postService.selectPostReport(postId);
    }

    @RequestMapping("/commentReport/{postCommentId}")
    public List<PostCommentReportDetailDTO>  commentPostReport(@PathVariable int postCommentId) {
        return postService.selectPostCommentReport(postCommentId);
    }
}
