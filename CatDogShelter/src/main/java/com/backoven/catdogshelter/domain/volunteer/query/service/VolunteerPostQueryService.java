package com.backoven.catdogshelter.domain.volunteer.query.service;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostCommentDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostFileDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostListDTO;
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


    public List<VolunteerPostListDTO> getVolunteerPostList() {
        return volunteerPostQueryMapper.selectVolunteerPostList();
    }

    public VolunteerPostDTO getVolunteerPost(int id) {

        List<VolunteerPostCommentDTO> comments = volunteerPostQueryMapper.selectVolunteerPostComments(id);

        List<VolunteerPostFileDTO> files = volunteerPostQueryMapper.selectVolunteerPostFiles(id);

        VolunteerPostDTO post = volunteerPostQueryMapper.selectVolunteerPost(id);

        post.setComments(comments);
        post.setFiles(files);

        return post;
    }

    public List<VolunteerPostListDTO> selectVolunteerPostsListByKeyword(String keyword) {
        return volunteerPostQueryMapper.selectVolunteerPostsListByKeyword(keyword);
    }
<<<<<<< HEAD

    public List<VolunteerPostListDTO> selectVolunteerPostsByView() {
        return volunteerPostQueryMapper.selectVolunteerPostsByView();
    }

    public List<VolunteerPostListDTO> selectVolunteerPostsByLiked() {
        return volunteerPostQueryMapper.selectVolunteerPostsByLiked();
    }
=======
>>>>>>> 33f796932cfb84fa1aacf92a4c46f369f22dd1ce
}
