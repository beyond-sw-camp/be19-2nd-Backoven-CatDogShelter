package com.backoven.catdogshelter.domain.adoption.command.application.controller.adminController;

import com.backoven.catdogshelter.domain.adoption.command.application.dto.AdoptionPostCommentReportDTO;
import com.backoven.catdogshelter.domain.adoption.command.application.dto.AdoptionPostReportDTO;
import com.backoven.catdogshelter.domain.adoption.command.application.service.adminService.AdoptionPostAdmindService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catdogshelter/admin")
@Tag(name="관리자 조회 API")
public class AdoptionPostAdminController {
    private final AdoptionPostAdmindService adoptionPostAdmindService;

    @Autowired
    public AdoptionPostAdminController(AdoptionPostAdmindService adoptionPostAdmindService) {
        this.adoptionPostAdmindService = adoptionPostAdmindService;
    }

    // 게시글 신고 조회 -> 관리자
    @Operation(summary = "입양 게시글 신고 조회 (관리자)", description = "관리자가 모든 입양 게시글 신고 내역을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = AdoptionPostReportDTO.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content)})
    @GetMapping("/adoption-post/report")
    public ResponseEntity<?> selectAllAdoptionPostReport(){
        List<AdoptionPostReportDTO> adoptionPostReport =
                adoptionPostAdmindService.selectAllAdoptionPostReport();
        return ResponseEntity.ok().body(adoptionPostReport);
    }
    // 댓글 신고 조회 -> 관리자
    @Operation(summary = "입양 게시글 댓글 신고 조회 (관리자)", description = "관리자가 모든 입양 게시글 댓글 신고 내역을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = AdoptionPostCommentReportDTO.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content)})
    @GetMapping("/adoption-post/comment/report")
    public ResponseEntity<?> selectAllAdoptionPostCommentReport(){
        List<AdoptionPostCommentReportDTO> adoptionPostCommentReport =
                adoptionPostAdmindService.selectAllAdoptionPostCommentReport();
        return ResponseEntity.ok().body(adoptionPostCommentReport);
    }
}
