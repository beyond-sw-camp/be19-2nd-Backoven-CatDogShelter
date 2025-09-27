package com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

}
