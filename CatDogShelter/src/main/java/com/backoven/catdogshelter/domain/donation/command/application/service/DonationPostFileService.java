package com.backoven.catdogshelter.domain.donation.command.application.service;


import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostFilesRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@RequiredArgsConstructor
public class DonationPostFileService {
    private final DonationPostRepository donationPostRepository;
    private final DonationPostFilesRepository donationPostFilesRepository;

//    private final String uploadDir = "/Users/dong/uploads/"; // 로컬 저장 경로
    private final String uploadDir = "/Users/haeone/Desktop/be19-2nd-backoven-petShelter/uploads/"; // 로컬 저장 경로


    public byte[] downloadImage(String fileName) {
        try {
            Path path = Paths.get(uploadDir + fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("이미지 다운로드 실패: " + fileName, e);
        }
    }
}
