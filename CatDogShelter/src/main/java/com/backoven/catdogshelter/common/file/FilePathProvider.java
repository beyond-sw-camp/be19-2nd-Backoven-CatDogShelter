package com.backoven.catdogshelter.common.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class FilePathProvider {

    private final FileProps fileProps;

    /**
     * 실제 저장할 물리 경로 생성(디렉토리 보장) 후, 완전한 경로를 반환
     */
    public String resolveNoticePath(String savedName) {
        String dateDir = LocalDate.now().toString().replace("-", ""); // yyyymmdd
        Path dir = Paths.get(fileProps.getUploadDir(), "notice", dateDir);
        try {
            Files.createDirectories(dir);
        } catch (Exception ignore) {}
        return dir.resolve(savedName).toString();
    }
}
