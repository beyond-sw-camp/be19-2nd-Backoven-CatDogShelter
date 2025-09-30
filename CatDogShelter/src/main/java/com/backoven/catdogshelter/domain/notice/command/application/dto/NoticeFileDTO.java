// NoticeFileDTO.java
package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

@Data
public class NoticeFileDTO {
    private String fileRename;
    private String filePath;
    // 클라에서 올린 시간을 신뢰하지 않기로 했으니 굳이 받지 않아도 됨
}
