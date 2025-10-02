package com.backoven.catdogshelter.domain.sighting.query.controller;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;
import com.backoven.catdogshelter.domain.sighting.query.service.QSightingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/sighting-post")
@Slf4j
@Tag(name = "목격 정보 게시글 조회 API")
public class QSightingController {

    private final QSightingService qSightingService;

    @Autowired
    public QSightingController(QSightingService QSightingService) {
        this.qSightingService = QSightingService;
    }

    // 목록 조회
    @GetMapping("/summary")
    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회한다. 입력을 통해서 조건 검색이 가능하고, 정렬에 대한 선택이 가능하다.")
    public List<SightingSummaryDTO> findSightingSummary(@ModelAttribute SightingSearchDTO search) {

//        List<SightingSummaryDTO> sightingSummaryDTO = sightingService.findSightingSummary();
//        return sightingSummaryDTO;
//        log.info("controller 계층: {}", search.toString());
        return qSightingService.findSightingSummary(search);
    }

    // 상세 조회
    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 조회", description = "게시글의 상세 내용을 조회한다.\n게시글에 달린 댓글, 작성자의 등급, 파일의 url 등을 만들기 위한 정보 등을 포함하고 있다.")
    public SightingDetailDTO findSightingDetails(@PathVariable int postId) {

        SightingDetailDTO sightingDetailDTO = qSightingService.findSightingDetails(postId);
        log.info("{}",  sightingDetailDTO);
        return sightingDetailDTO;
    }

    // 게시글 신고 조회
    @GetMapping("/post-report/{postId}")
    @Operation(summary = "게시글 신고 조회", description = "입력된 값을 id로 가지는 게시글이 신고당한 횟수와 각 신고 내용을 조회한다.")
    public SightingPostReportCountDTO findSightingPostReport(@PathVariable int postId) {
        List<SightingPostReportDTO> reports = qSightingService.findSightingPostReport(postId);
        SightingPostReportCountDTO response = new SightingPostReportCountDTO();
        response.setCount(reports.size());
        response.setSightingPostReportDTOList(reports);

        return response;
    }

    // 댓글 신고 조회
    @GetMapping("/comment-report/{commentId}")
    @Operation(summary = "댓글 신고 조회", description = "입력된 값을 id로 가지는 댓글이 신고당한 횟수와 각 신고 내용을 조회한다.")
    public SightingPostCommentReportCountDTO findSightingPostCommentReport(@PathVariable int commentId) {
        List<SightingPostCommentReportDTO> reports = qSightingService.findSightingPostCommentReport(commentId);
        SightingPostCommentReportCountDTO response = new SightingPostCommentReportCountDTO();
        response.setCount(reports.size());
        response.setSightingPostCommentReportDTOList(reports);

        return response;
    }

    // 파일 조회
    @Operation(summary = "파일 조회", description = "상세 조회에서 얻은 filePath와 fileName을 입력값으로 이용해 실제 이미지를 조회한다.")
    @GetMapping("/file/{filePath}/{fileName}")
    public ResponseEntity<Resource> getSightingPostFile(@PathVariable String filePath,
                                            @PathVariable String fileName,
                                            @Value("${file.upload-dir}") String commonPath) throws IOException {
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
