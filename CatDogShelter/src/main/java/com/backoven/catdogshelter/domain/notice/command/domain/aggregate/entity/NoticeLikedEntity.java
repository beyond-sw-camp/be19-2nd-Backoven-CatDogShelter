package com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@Table(name = "noticeliked",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"notice_id", "user_id"}),
                @UniqueConstraint(columnNames = {"notice_id", "user_id"})
        })
public class NoticeLikedEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notice_id", nullable = false)
    private Long noticeId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "head_id")
    private Long headId;

    @PrePersist @PreUpdate
    private void ensureExclusiveActor() {
        boolean u = userId != null;
        boolean h = headId != null;
        if (u == h) throw new IllegalStateException("Exactly one of userId or headId must be set.");
    }
}
