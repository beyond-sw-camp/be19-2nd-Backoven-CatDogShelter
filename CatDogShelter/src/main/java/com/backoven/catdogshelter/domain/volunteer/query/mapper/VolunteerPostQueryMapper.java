package com.backoven.catdogshelter.domain.volunteer.query.mapper;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolunteerPostQueryMapper {
    List<VolunteerPostDTO> selectAllVolunteerPosts();

    List<VolunteerPostDTO> selectAllVolunteerPostsByNoneProcessed();
}
