package com.backoven.catdogshelter.domain.post.command.application.controller;

import com.backoven.catdogshelter.domain.post.command.application.dto.*;
import com.backoven.catdogshelter.domain.post.command.application.service.PostService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostExController {

    private final PostService postService;

    public PostExController(PostService postService) {
        this.postService = postService;
    }


    @RequestMapping("/regist")
    public void registPost(@RequestBody PostRegistDTO postregist){
        postService.registPost(postregist);
    }

    @RequestMapping("/modify")
    public void modifyPost(@RequestBody PostModifyDTO postmodify){
        postService.modifyPost(postmodify);
    }

    /* 프론트에서 삭제할 자유게시글 번호만 받아서 사용하기 때문에 DTO을 따로 사용하지 않고 Map을 사용해서
       String 타입으로 받은 후에 다시 int 타입으로 전환 */
    @RequestMapping("/delete")
    public void deletePost(@RequestBody Map<String, String> postDelete){
        int id = Integer.parseInt(postDelete.get("id"));
        postService.deletePost(id);
    }

    @RequestMapping("/comment/regist")
    public void registPostComment(@RequestBody PostCommentRegistDTO postCommentRegist){
        postService.registPostComment(postCommentRegist);
    }

    @RequestMapping("/comment/modify")
    public void modifyPostComment(@RequestBody PostCommentModifyDTO postCommentModify){
        postService.modifyPostComment(postCommentModify);
    }

    @RequestMapping("/comment/delete")
    public void deletePostComment(@RequestBody Map<String, String> postCommentDelete){
        int id = Integer.parseInt(postCommentDelete.get("id"));
        postService.deletePostComment(id);
    }

    @RequestMapping("/files/regist")
    public void registPostFiles(@RequestBody PostFilesRegistDTO postFilesRegist){
        postService.registPostFiles(postFilesRegist);
    }

    @RequestMapping("/files/modify")
    public void modifyPostFiles(@RequestBody PostFilesModifyDTO postFilestModify){
        postService.modifyPostFiles(postFilestModify);
    }

    @RequestMapping("/files/delete")
    public void deletePostFiles(@RequestBody PostFilesDeleteDTO postFilesDelete){
        postService.deletePostFiles(postFilesDelete);
    }


    @RequestMapping("/liked")
    public void likedPost(@RequestBody PostLikedDTO postLiked){
        postService.likedPost(postLiked);
    }

    @RequestMapping("/report")
    public void reportPost(@RequestBody PostReportDTO postReport){
        postService.reportPost(postReport);
    }
    @RequestMapping("/comment/report")
    public void reportPostComment(@RequestBody PostCommentReportDTO postCommentReport){
        postService.reportPostComment(postCommentReport);
    }

}

