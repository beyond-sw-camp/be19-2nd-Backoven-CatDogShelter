package com.backoven.catdogshelter.domain.post.command.application.controller;

import com.backoven.catdogshelter.domain.post.command.application.dto.PostModifyDTO;
import com.backoven.catdogshelter.domain.post.command.application.dto.PostRegistDTO;
import com.backoven.catdogshelter.domain.post.command.application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostExController {

    private final PostService postservice;

    @Autowired
    public PostExController(PostService postservice) {
        this.postservice = postservice;
    }

    @RequestMapping("/regist")
    public void insertPost(@RequestBody PostRegistDTO postregist){
        postservice.registPost(postregist);
    }

    @RequestMapping("/modify")
    public void modifyPost(@RequestBody PostModifyDTO postmodify){
        postservice.modifyPost(postmodify);
    }

    /* 프론트에서 삭제할 자유게시글 번호만 받아서 사용하기 때문에 DTO을 따로 사용하지 않고 Map을 사용해서
       String 타입으로 받은 후에 다시 int 타입으로 전환 */

    @RequestMapping("/delete")
    public void deletePost(@RequestBody Map<String, String> request){
        int id = Integer.parseInt(request.get("id"));
        postservice.deletePost(id);
    }


}
