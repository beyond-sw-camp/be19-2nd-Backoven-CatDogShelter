package com.backoven.catdogshelter.domain.missing.command.application.service;

import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostFilesRepository;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Transactional
public class MissingPostFileService {
    private final MissingPostRepository missingPostRepository;
    private final MissingPostFilesRepository missingPostFilesRepository;

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
