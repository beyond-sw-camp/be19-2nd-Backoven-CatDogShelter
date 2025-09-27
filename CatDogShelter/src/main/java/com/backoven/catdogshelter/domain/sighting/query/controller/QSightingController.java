package com.backoven.catdogshelter.domain.sighting.query.controller;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;
import com.backoven.catdogshelter.domain.sighting.query.service.QSightingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/sighting-post")
@Slf4j
public class QSightingController {

    private final QSightingService qSightingService;

    @Autowired
    public QSightingController(QSightingService QSightingService) {
        this.qSightingService = QSightingService;
    }

    // 목록 조회
    @GetMapping("/summary")
    public List<SightingSummaryDTO> findSightingSummary(@ModelAttribute SightingSearchDTO search) {

//        List<SightingSummaryDTO> sightingSummaryDTO = sightingService.findSightingSummary();
//        return sightingSummaryDTO;
//        log.info("controller 계층: {}", search.toString());
        return qSightingService.findSightingSummary(search);
    }

    // 상세 조회
    @GetMapping("/{postId}")
    public SightingDetailDTO findSightingDetails(@PathVariable int postId) {

        SightingDetailDTO sightingDetailDTO = qSightingService.findSightingDetails(postId);
        log.info("{}",  sightingDetailDTO);
        return sightingDetailDTO;
    }

    // 게시글 신고 조회
    @GetMapping("/post-report/{postId}")
    public List<SightingPostReportDTO> findSightingPostReport(@PathVariable int postId) {
        return qSightingService.findSightingPostReport(postId);
    }

    // 댓글 신고 조회
    @GetMapping("/comment-report/{commentId}")
    public List<SightingPostCommentReportDTO> findSightingPostCommentReport(@PathVariable int commentId) {
        return qSightingService.findSightingPostCommentReport(commentId);
    }

    @GetMapping("/file/{filePath}/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String filePath,
                                            @PathVariable String fileName,
                                            @Value("${filepath}") String commonPath) throws IOException {
        Path path = Paths.get(commonPath + "/" + filePath + "/" + fileName);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(path);
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        HttpHeaders headers =new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
