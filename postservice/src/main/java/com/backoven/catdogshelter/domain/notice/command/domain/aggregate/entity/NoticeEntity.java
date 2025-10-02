package com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "notice")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    // 서버 시간 자동
    @Column(name = "created_at", length = 20, nullable = false)
    private String createdAt;

    @Column(name = "updated_at", length = 20)
    private String updatedAt; // nullable

    @Column(name = "rating_id", nullable = false)
    private Integer ratingId;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoticeFileEntity> files = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoticeLikedEntity> likes = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = DateTimeUtil.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = DateTimeUtil.now();
    }

    /** 파일만 수정해도 수정시간 반영하고 싶을 때 호출 */
    public void touch() {
        this.updatedAt = DateTimeUtil.now();
    }

    // 편의 메서드
    public void addFile(NoticeFileEntity f) {
        f.setNotice(this);
        this.files.add(f);
    }
    public void removeFile(NoticeFileEntity f) {
        f.setNotice(null);
        this.files.remove(f);
    }
}
