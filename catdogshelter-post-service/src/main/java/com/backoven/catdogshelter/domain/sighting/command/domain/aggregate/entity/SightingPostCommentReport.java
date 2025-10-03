package com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sightingPostCommentReport")
public class SightingPostCommentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportCategory reportCategory;

    @Column(name = "etc_detail")
    private String etcDetail;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "status", nullable = false)
    private boolean status = false;

    @Column(name = "comment_id", nullable = false)
    private int commentId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "head_id")
    private Integer headId;
}
