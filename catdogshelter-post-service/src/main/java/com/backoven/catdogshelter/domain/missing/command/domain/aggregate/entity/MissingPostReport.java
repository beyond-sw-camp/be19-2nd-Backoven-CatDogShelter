package com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "missingPostReport",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "user_id"}),
        @UniqueConstraint(columnNames = {"post_id", "head_id"})
})
public class MissingPostReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ReportCategory category;   // 신고 카테고리

    @Column(name = "etc_detail")
    private String etcDetail;          // 신고 세부 사유

    @Column(name = "created_at")
    private String createdAt;

    private boolean status = false;    // 처리 여부 (기본 false)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private MissingPost post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id")
    private ShelterheadEntity head;
}
