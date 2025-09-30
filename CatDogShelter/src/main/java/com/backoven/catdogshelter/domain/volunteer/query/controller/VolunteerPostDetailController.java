// src/main/java/com/backoven/catdogshelter/domain/volunteer/query/controller/VolunteerPostDetailController.java
package com.backoven.catdogshelter.domain.volunteer.query.controller;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostDetailDTO;
import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerPostDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/volunteer-posts/post")
public class VolunteerPostDetailController {

    private final VolunteerPostDetailService volunteerPostDetailService;

    // 상세 조회
    // - inc=true(default) : 조회수 +1
    // - inc=false         : 조회수 증가 없이 조회

    @GetMapping(value = {"/{id}", "/"})
    public VolunteerPostDetailDTO getOne(@PathVariable Integer id,
                                         @RequestParam(name = "inc", required = false, defaultValue = "true") boolean inc) {
        return volunteerPostDetailService.getDetail(id, inc);
    }
}
