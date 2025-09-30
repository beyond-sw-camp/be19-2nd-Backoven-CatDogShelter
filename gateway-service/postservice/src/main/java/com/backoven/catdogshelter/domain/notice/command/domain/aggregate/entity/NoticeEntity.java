package com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@Table(name="notice")
public class NoticeEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", length = 100, nullable = false)
    private String title;

    @Lob
    @Column(name="content", nullable = false)
    private String content;

    @Column(name="created_at", length = 20, nullable = false)
    private String createdAt;

    @Column(name="updated_at", length = 20, nullable = true)
    private String updatedAt;

    @Column(name="rating_id", nullable = false)
    private Long ratingId;

    @Column(name="is_deleted", nullable = false)
    private boolean  deleted = false;
}
