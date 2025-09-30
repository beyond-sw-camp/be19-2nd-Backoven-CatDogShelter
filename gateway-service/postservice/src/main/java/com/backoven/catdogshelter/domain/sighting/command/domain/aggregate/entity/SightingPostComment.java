package com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sightingPostComment")
public class SightingPostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "is_blinded", nullable = false)
    private boolean isBlinded = false;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "post_id", nullable = false)
    private int postId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "head_id")
    private Integer headId;
}
