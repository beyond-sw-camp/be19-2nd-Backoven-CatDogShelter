package com.backoven.catdogshelter.domain.volunteerking.query.controller;

import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingMonthDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingTotalDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.service.QVolunteerKingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/volunteer-king")
public class QVolunteerKingController {

    private final QVolunteerKingService qVolunteerKingService;

    @Autowired
    public QVolunteerKingController(@Qualifier("QVolunteerKingServiceImpl") QVolunteerKingService qVolunteerKingService) {
        this.qVolunteerKingService = qVolunteerKingService;
    }

    // 월별 봉사왕 조회
    @GetMapping("/month")
    public List<VolunteerKingMonthDTO> findVolunteerKingMonth() {
        return qVolunteerKingService.findVolunteerKingMonth();
    }

    // 누적 봉사왕 조회
    @GetMapping("/total")
    public List<VolunteerKingTotalDTO> findVolunteerKingTotal() {
        return qVolunteerKingService.findVolunteerKingTotal();
    }

    // 특정 회원의 봉사왕 기록 조회
    @GetMapping("/{userId}")
    public List<VolunteerKingMonthDTO> findVolunteerKingByUserId(@PathVariable int userId) {
        return qVolunteerKingService.findVolunteerKingByUserId(userId);
    }


}
