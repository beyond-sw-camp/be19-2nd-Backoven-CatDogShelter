package com.backoven.catdogshelter.domain.volunteer.query.controller;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationQueryDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationSearchCond;
import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerAssociationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "봉사모임 API")
@RestController
@RequestMapping("/association-posts")
public class VolunteerAssociationQueryController {

    private final VolunteerAssociationQueryService volunteerAssociationQueryService;

    @Autowired
    public VolunteerAssociationQueryController(
            VolunteerAssociationQueryService volunteerAssociationQueryService) {
        this.volunteerAssociationQueryService = volunteerAssociationQueryService;
    }

    // 단건 조회
    @Operation(summary = "게시글 조회", description = "게시판 이용자는 하나의 게시글을 조회할 수 있다.")
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerAssociationQueryDTO> selectVolunteerAssociation(@PathVariable Integer id) {
        var dto = volunteerAssociationQueryService.selectVolunteerAssociation(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    // 목록 조회 (검색+페이징)
    @Operation(summary = "게시글 목록 조회", description = "게시판 이용자는 검색 또는 페이지로 목록을 조회할 수 있다.")
    @GetMapping(value = {"/","/search"})
    public ResponseEntity<List<VolunteerAssociationQueryDTO>> selectVolunteerAssociationsBySearch(
            @RequestParam(required = false) Integer sigunguId,
            @RequestParam(required = false) Integer headId,
            @RequestParam(required = false) Boolean deadline,
            @RequestParam(required = false, name = "isEnd") Boolean isEnd,

            @RequestParam(required = false) String keyword,

            @RequestParam(required = false) String startDateFrom,
            @RequestParam(required = false) String startDateTo,

            @RequestParam(required = false, defaultValue = "createdAt") String orderBy,
            @RequestParam(required = false, defaultValue = "DESC") String orderDir,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset
    ) {
        VolunteerAssociationSearchCond cond = VolunteerAssociationSearchCond.builder()
                .sigunguId(sigunguId)
                .headId(headId)
                .deadline(deadline)
                .isEnd(isEnd)
                .keyword(keyword)
                .startDateFrom(startDateFrom)
                .startDateTo(startDateTo)
                .orderBy(orderBy)
                .orderDir(orderDir)
                .limit(limit)
                .offset(offset)
                .build();

        List<VolunteerAssociationQueryDTO> list =
                volunteerAssociationQueryService.selectVolunteerAssociationsBySearch(cond);
        return ResponseEntity.ok(list);
    }
}
