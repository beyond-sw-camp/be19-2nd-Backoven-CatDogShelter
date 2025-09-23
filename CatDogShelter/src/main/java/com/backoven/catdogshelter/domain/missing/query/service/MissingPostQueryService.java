package com.backoven.catdogshelter.domain.missing.query.service;

import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDTO;
import com.backoven.catdogshelter.domain.missing.query.mapper.MissingPostQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissingPostQueryService {

    private final MissingPostQueryMapper mapper;

    public List<MissingPostQueryDTO> searchPosts(String keyword, int page, int size) {
        int offset = (page - 1) * size;
        return mapper.searchPosts(keyword, offset, size);
    }

    public List<MissingPostQueryDTO> getPopularPosts(int limit) {
        return mapper.findPopularPosts(limit);
    }

    public List<MissingPostQueryDTO> getLatestPosts(int limit) {
        return mapper.findLatestPosts(limit);
    }

    public void increaseView(Long id) {
        mapper.increaseView(id);
    }

    public MissingPostQueryDTO getPostById(Long id) {
        MissingPostQueryDTO dto = mapper.selectPostById(id);
        if (dto == null) {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }
        return dto;
    }

}
