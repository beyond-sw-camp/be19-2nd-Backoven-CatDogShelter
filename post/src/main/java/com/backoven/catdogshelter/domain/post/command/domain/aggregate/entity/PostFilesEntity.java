package com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity;


import com.backoven.catdogshelter.common.util.DateTimeUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="postfiles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="file_rename")
    private String fileRename;

    @Column(name="uploaded_at")
    private String uploadedAt;

    @Column(name="file_path")
    private String filePath;

    @Column(name="post_id")
    private int postId;


    // util 폴더 안 DateTimeUtil 클래스를 통해 현재 시간 표시
    @PrePersist
    public void prePersist() {
        this.uploadedAt = DateTimeUtil.now();
    }

}
