package com.backoven.catdogshelter.domain.volunteer.query.service;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostDTO;
import com.backoven.catdogshelter.domain.volunteer.query.mapper.VolunteerPostQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerPostQueryService {
    private final VolunteerPostQueryMapper volunteerPostQueryMapper;

    @Autowired
    public VolunteerPostQueryService(VolunteerPostQueryMapper volunteerPostQueryMapper) {
        this.volunteerPostQueryMapper = volunteerPostQueryMapper;
    }

    public List<VolunteerPostDTO> selectAllVolunteerPosts() {
        return volunteerPostQueryMapper.selectAllVolunteerPosts();
    }


    public List<VolunteerPostDTO> selectAllVolunteerPostsByNoneProcessed() {
        return volunteerPostQueryMapper.selectAllVolunteerPostsByNoneProcessed();
    }

}
