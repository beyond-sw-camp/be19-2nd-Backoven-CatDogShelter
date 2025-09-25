package com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="postcomment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCommentEntity {
    @Id
    //auto_increment 적용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private String content;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;

    @Column(name="is_deleted")
    private Integer isDeleted = 0;

    @Column(name="post_id")
    private int postId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="head_id")
    private Integer headId;
}
