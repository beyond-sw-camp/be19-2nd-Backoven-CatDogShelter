package com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@Table(name = "noticefiles")
public class NoticeFileEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notice_id", nullable = false)
    private Long noticeId;

    @Column(name = "file_rename", length = 120, nullable = false)
    private String fileRename;

    @Column(name = "file_path", length = 255, nullable = false)
    private String filePath;

    @Column(name = "uploaded_at", length = 20, nullable = false)
    private String uploadedAt;
}
