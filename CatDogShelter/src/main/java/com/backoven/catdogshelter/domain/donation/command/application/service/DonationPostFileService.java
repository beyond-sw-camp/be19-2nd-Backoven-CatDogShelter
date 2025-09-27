package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostFiles;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostFilesRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DonationPostFileService {
    private final DonationPostRepository donationPostRepository;
    private final DonationPostFilesRepository donationPostFilesRepository;

    private final String uploadDir = "/Users/dong/uploads/"; // 로컬 저장 경로

    @Transactional
    public void uploadFiles(Long postId, List<MultipartFile> files) {
        DonationPost post = donationPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        for (MultipartFile file : files) {
            try {
                // 파일명 변환
                String rename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + rename);

                // 서버에 저장
                file.transferTo(path);

                // DB에 경로 저장
                DonationPostFiles postFile = new DonationPostFiles();
                postFile.setFileRename(rename);
                postFile.setFilePath("/uploads/" + rename); // URL로 접근할 경로
                postFile.setUploadedAt(DateTimeUtil.now());
                postFile.setPost(post);

                donationPostFilesRepository.save(postFile);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }
        }
    }
}
