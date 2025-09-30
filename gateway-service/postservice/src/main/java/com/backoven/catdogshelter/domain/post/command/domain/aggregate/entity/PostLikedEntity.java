package com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="postliked")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostLikedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="post_id")
    private int postId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="head_id")
    private Integer headId;
}
