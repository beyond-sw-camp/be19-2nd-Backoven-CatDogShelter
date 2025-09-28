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
import java.nio.file.Files;
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


    public byte[] downloadImage(String fileName) {
        try {
            Path path = Paths.get(uploadDir + fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("이미지 다운로드 실패: " + fileName, e);
        }
    }
}
