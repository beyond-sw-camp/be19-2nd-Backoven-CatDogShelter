package com.backoven.catdogshelter.domain.volunteerking.query.controller;

import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingMonthDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingTotalDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.service.QueryVolunteerKingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/volunteer-king")
@Tag(name = "봉사왕 조회 API")
public class QueryVolunteerKingController {

    private final QueryVolunteerKingService queryVolunteerKingService;

    @Autowired
    public QueryVolunteerKingController(@Qualifier("queryVolunteerKingServiceImpl") QueryVolunteerKingService queryVolunteerKingService) {
        this.queryVolunteerKingService = queryVolunteerKingService;
    }

    // 월별 봉사왕 조회
    @GetMapping("/month")
    @Operation(summary = "월별 봉사왕 조회", description = "월별 봉사왕 리스트를 조회합니다.")
    public List<VolunteerKingMonthDTO> findVolunteerKingMonth() {
        return queryVolunteerKingService.findVolunteerKingMonth();
    }

    // 누적 봉사왕 조회
    @GetMapping("/total")
    @Operation(summary = "누적 봉사왕 조회", description = "누적 봉사왕을 조회합니다.")
    public List<VolunteerKingTotalDTO> findVolunteerKingTotal() {
        return queryVolunteerKingService.findVolunteerKingTotal();
    }

    // 특정 회원의 봉사왕 기록 조회
    @GetMapping("/{userId}")
    @Operation(summary = "유저의 봉사왕 기록 조회", description = "유저 개인의 봉사왕 기록을 리스트로 조회합니다.")
    public List<VolunteerKingMonthDTO> findVolunteerKingByUserId(@PathVariable int userId) {
        return queryVolunteerKingService.findVolunteerKingByUserId(userId);
    }


}
