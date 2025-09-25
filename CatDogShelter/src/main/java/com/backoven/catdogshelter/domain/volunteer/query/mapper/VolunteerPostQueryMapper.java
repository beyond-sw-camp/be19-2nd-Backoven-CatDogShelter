package com.backoven.catdogshelter.domain.volunteer.query.mapper;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostCommentDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostFileDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolunteerPostQueryMapper {

    List<VolunteerPostListDTO> selectVolunteerPostList();


    List<VolunteerPostCommentDTO> selectVolunteerPostComments(int id);
    List<VolunteerPostFileDTO> selectVolunteerPostFiles(int id);
    VolunteerPostDTO selectVolunteerPost(int id);

    List<VolunteerPostListDTO> selectVolunteerPostsListByKeyword(String keyword);
<<<<<<< HEAD

    List<VolunteerPostListDTO> selectVolunteerPostsByView();

    List<VolunteerPostListDTO> selectVolunteerPostsByLiked();
=======
>>>>>>> 33f796932cfb84fa1aacf92a4c46f369f22dd1ce
}
