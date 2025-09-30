// src/main/java/com/backoven/catdogshelter/domain/volunteer/query/controller/VolunteerApplicationQueryController.java
package com.backoven.catdogshelter.domain.volunteer.query.controller;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerApplicationDTO;
import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerApplicationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/association-posts")
public class VolunteerApplicationQueryController {

    private final VolunteerApplicationQueryService volunteerApplicationQueryService;

    @Autowired
    public VolunteerApplicationQueryController(
            VolunteerApplicationQueryService volunteerApplicationQueryService) {
        this.volunteerApplicationQueryService = volunteerApplicationQueryService;
    }

    /** 단건 조회 (신청 id 기준) */
    @GetMapping("/application-details/{id}")
    public VolunteerApplicationDTO getOne(@PathVariable Integer id) {
        return volunteerApplicationQueryService.getById(id);
    }

    /** 특정 봉사모임(volunteerAssociation)의 신청자 목록 */
    @GetMapping("/{volunteerId}/application-list")
    public List<VolunteerApplicationDTO> getListByVolunteer(
            @PathVariable Integer volunteerId,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        return volunteerApplicationQueryService.getListByVolunteerId(volunteerId, offset, limit);
    }
}
