package com.backoven.catdogshelter.domain.adoption.command.application.controller.adminController;

import com.backoven.catdogshelter.domain.adoption.command.application.dto.AdoptionPostCommentReportDTO;
import com.backoven.catdogshelter.domain.adoption.command.application.dto.AdoptionPostReportDTO;
import com.backoven.catdogshelter.domain.adoption.command.application.service.adminService.AdoptionPostAdmindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catdogshelter/admin")
public class AdoptionPostAdminController {
    private final AdoptionPostAdmindService adoptionPostAdmindService;

    @Autowired
    public AdoptionPostAdminController(AdoptionPostAdmindService adoptionPostAdmindService) {
        this.adoptionPostAdmindService = adoptionPostAdmindService;
    }

    // 게시글 신고 조회 -> 관리자
    @GetMapping("/adoption-post/report")
    public ResponseEntity<?> selectAllAdoptionPostReport(){
        List<AdoptionPostReportDTO> adoptionPostReport =
                adoptionPostAdmindService.selectAllAdoptionPostReport();
        return ResponseEntity.ok().body(adoptionPostReport);
    }
    // 댓글 신고 조회 -> 관리자
    @GetMapping("/adoption-post/comment/report")
    public ResponseEntity<?> selectAllAdoptionPostCommentReport(){
        List<AdoptionPostCommentReportDTO> adoptionPostCommentReport =
                adoptionPostAdmindService.selectAllAdoptionPostCommentReport();
        return ResponseEntity.ok().body(adoptionPostCommentReport);
    }
}
