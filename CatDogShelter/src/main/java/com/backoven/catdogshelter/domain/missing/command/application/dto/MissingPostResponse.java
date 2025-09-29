package com.backoven.catdogshelter.domain.missing.command.application.dto;


import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostFiles;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MissingPostResponse {
    private Integer id;
    private String title;
    private String content;
    private String createdAt;
    private Integer view;
    private HeadResponse head;
    private List<FileResponse> files;

    public MissingPostResponse(MissingPost post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.view = post.getView();

        // head 엔티티 -> DTO
        if (post.getHead() != null) {
            this.head = new HeadResponse(post.getHead().getId(), post.getHead().getCeoName());
        }

        // files 엔티티 리스트 -> DTO 리스트
        if (post.getFiles() != null) {
            this.files = post.getFiles().stream()
                    .map(FileResponse::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class HeadResponse {
        private Integer id;
        private String userName;

        public HeadResponse(Integer id, String userName) {
            this.id = id;
            this.userName = userName;
        }
    }

    @Getter
    public static class FileResponse {
        private Integer id;
        private String fileRename;
        private String filePath;
        private String uploadedAt;

        public FileResponse(MissingPostFiles file) {
            this.id = file.getId();
            this.fileRename = file.getFileRename();
            this.filePath = file.getFilePath();
            this.uploadedAt = file.getUploadedAt();
        }
    }
}

