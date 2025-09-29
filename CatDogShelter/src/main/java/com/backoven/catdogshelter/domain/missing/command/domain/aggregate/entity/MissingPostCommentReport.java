package com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.ReportCategory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "missingPostCommentReport",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"comment_id", "user_id"}),
                @UniqueConstraint(columnNames = {"comment_id", "head_id"})
        })
public class MissingPostCommentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ReportCategory category;

    @Column(name = "etc_detail")
    private String etcDetail;

    @Column(name = "created_at")
    private String createdAt;

    // 처리 여부 (예: false=미처리, true=처리됨)
    @Column(nullable = false)
    private boolean status = false;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private MissingPostComment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id")
    private ShelterHeadEntity head;
}
