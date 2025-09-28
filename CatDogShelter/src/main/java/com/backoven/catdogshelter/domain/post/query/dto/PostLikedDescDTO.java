package com.backoven.catdogshelter.domain.post.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostLikedDescDTO {
    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private String writeType;
    private String writeName;
    private int likeCount;

}
