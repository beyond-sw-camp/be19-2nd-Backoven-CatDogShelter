package com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "noticefiles")
public class NoticeFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false, foreignKey = @ForeignKey(name = "fk_nf_notice"))
    private NoticeEntity notice;

    @Column(name = "file_rename", length = 100, nullable = false)
    private String fileRename;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "uploaded_at", length = 20, nullable = false)
    private String uploadedAt;

    @PrePersist
    protected void onUpload() {
        if (this.uploadedAt == null) {
            this.uploadedAt = DateTimeUtil.now();
        }
    }
}
