package com.backoven.catdogshelter.domain.post.query.dto;
/* 자유게시글에 올린 파일을 조회할 때 사용되는 DTO */


public class PostFilesDTO {
    private int id;
    private String file_rename;
    private String file_path;
    private String uploaded_at;
    private int post_id;            // 자유게시글(post)

}
