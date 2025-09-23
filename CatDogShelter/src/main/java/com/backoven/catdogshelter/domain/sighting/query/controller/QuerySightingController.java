package com.backoven.catdogshelter.domain.sighting.query.controller;

import com.backoven.catdogshelter.domain.sighting.query.dto.SightingDetailDTO;
import com.backoven.catdogshelter.domain.sighting.query.dto.SightingSearchDTO;
import com.backoven.catdogshelter.domain.sighting.query.dto.SightingSummaryDTO;
import com.backoven.catdogshelter.domain.sighting.query.service.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sighting-posts")
public class QuerySightingController {

    private final SightingService sightingService;

    @Autowired
    public QuerySightingController(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @GetMapping("/summary")
    public List<SightingSummaryDTO> findSightingSummary(@ModelAttribute SightingSearchDTO search) {

//        List<SightingSummaryDTO> sightingSummaryDTO = sightingService.findSightingSummary();
//        return sightingSummaryDTO;
        return sightingService.findSightingSummary(search);
    }


    //    @GetMapping("/detail/{postId}")
//    public SightingDetailDTO findSightingDetails(@PathVariable int postId) {
//
//        SightingDetailDTO sightingDetailDTO = sightingService.findSightingDetails(postId);
//
//        return null;
//    }

}
