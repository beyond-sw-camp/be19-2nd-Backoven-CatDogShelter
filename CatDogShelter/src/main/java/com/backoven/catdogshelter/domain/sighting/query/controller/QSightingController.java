package com.backoven.catdogshelter.domain.sighting.query.controller;

import com.backoven.catdogshelter.domain.sighting.query.dto.SightingDetailDTO;
import com.backoven.catdogshelter.domain.sighting.query.dto.SightingSearchDTO;
import com.backoven.catdogshelter.domain.sighting.query.dto.SightingSummaryDTO;
import com.backoven.catdogshelter.domain.sighting.query.service.QSightingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sighting-posts")
@Slf4j
public class QSightingController {

    private final QSightingService qSightingService;

    @Autowired
    public QSightingController(QSightingService QSightingService) {
        this.qSightingService = QSightingService;
    }

    @GetMapping("/summary")
    public List<SightingSummaryDTO> findSightingSummary(@ModelAttribute SightingSearchDTO search) {

//        List<SightingSummaryDTO> sightingSummaryDTO = sightingService.findSightingSummary();
//        return sightingSummaryDTO;
        log.info("controller 계층: {}", search.toString());
        return qSightingService.findSightingSummary(search);
    }

    @GetMapping("/{postId}")
    public SightingDetailDTO findSightingDetails(@PathVariable int postId) {

        SightingDetailDTO sightingDetailDTO = qSightingService.findSightingDetails(postId);

        return sightingDetailDTO;
    }
}
