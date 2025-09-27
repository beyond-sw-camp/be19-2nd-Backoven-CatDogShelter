package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.Data;

@Data
public class VolunteerPostListDTO {
    private int id;           // post_id AS id
    private String title;
    private String userName;
    private String createdAt;  // VARCHAR(20) 스키마라 String
    private int view;
    private int likeCount; // IFNULL(likes,0) AS likeCount
}
