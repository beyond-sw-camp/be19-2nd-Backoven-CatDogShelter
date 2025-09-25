package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VolunteerPostDTO {
    private int id;             // 게시글 번호
    private String title;       // 제목
    private String content;
    private String createdAt;
    private String updatedAt;
    private String writerName;
    private int view;
    private int likeCount;
    private List<VolunteerPostCommentDTO> comments;
    private List<VolunteerPostFileDTO> files;

}
