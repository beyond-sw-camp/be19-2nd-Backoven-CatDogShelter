package com.backoven.catdogshelter.domain.donation.command.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostFiles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DonationPostResponse {
    private Integer id;
    private String title;
    private String content;
    private String createdAt;
    private Integer view;
    private HeadResponse head;
    private List<FileResponse> files;

    public DonationPostResponse(DonationPost post) {
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

        public FileResponse(DonationPostFiles file) {
            this.id = file.getId();
            this.fileRename = file.getFileRename();
            this.filePath = file.getFilePath();
            this.uploadedAt = file.getUploadedAt();
        }
    }
}
