package com.backoven.catdogshelter.common.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "file")
public class FileProps {
    private String uploadDir; // yml의 file.upload-dir
}
