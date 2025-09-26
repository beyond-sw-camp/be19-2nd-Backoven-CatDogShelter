package com.backoven.catdogshelter.domain.sighting.command.application.service;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostDTO;
import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPost;
import com.backoven.catdogshelter.domain.sighting.command.domain.repository.SightingPostRepository;
import com.backoven.catdogshelter.domain.sighting.command.domain.service.DSightingService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ASightingServiceImpl implements ASightingService {

    private final DSightingService dSightingService;
    private final SightingPostRepository sightingPostRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ASightingServiceImpl(DSightingService DSightingService, SightingPostRepository sightingPostRepository, ModelMapper modelMapper) {
        this.dSightingService = DSightingService;
        this.sightingPostRepository = sightingPostRepository;
        this.modelMapper = modelMapper;
    }

    // 게시글 작성
    @Override
    public int registSightingPost(RequestSightingPostDTO newPostDTO) {

        // 추가하려는 게시글이 규칙을 지키지 않았다면
        dSightingService.validatePost(newPostDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);  // 정확하게 변수명이 일치하는 것만 매핑
                                                                                        // id 라는 말이 들어가는게 많으므로 설정
        SightingPost newPost = modelMapper.map(newPostDTO, SightingPost.class);

        // createdAt을 서버의 현재시간으로 설정: yyyy-MM-dd HH:mm:ss
        newPost.setCreatedAtNow();

        // 게시글 insert
        sightingPostRepository.save(newPost);

        // 게시글 번호 반환
        return newPost.getId();
    }

    @Override
    public void modifySightingPost(int postId, RequestSightingPostDTO modifyPostDTO) {

        // 변경하려는 게시글이 규칙을 지키지 않았다면
        dSightingService.validatePost(modifyPostDTO);

        // 엔티티로 변경
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPost modifyPost = modelMapper.map(modifyPostDTO, SightingPost.class);

        // DB에서 정보 불러오기
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        if (foundPost != null) {
            // 정보 변경 + 업데이트 날짜 추가
            foundPost.setTitle(modifyPost.getTitle());
            foundPost.setContent(modifyPost.getContent());
            foundPost.setSightedAt(modifyPost.getSightedAt());
            foundPost.setSightedPlace(modifyPost.getSightedPlace());
            foundPost.setColor(modifyPost.getColor());
            foundPost.setAnimalType(modifyPost.getAnimalType());
            foundPost.setBreed(modifyPost.getBreed());
            foundPost.setSigunguId(modifyPost.getSigunguId());
            foundPost.setUpdatedAtNow();
        }
    }

    @Override
    public boolean removeSightingPost(int postId) {
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        if(foundPost != null) {
            foundPost.setDeleted(true);
            return true;
        }

        return false;
    }

    @Override
    public boolean blindSightingPost(int postId) {
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        if(foundPost != null) {
            foundPost.setBlinded(true);
            return true;
        }

        return false;
    }

    @Override
    public boolean restoreSightingPost(int postId) {
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        if(foundPost != null) {
            foundPost.setBlinded(false);
            return true;
        }

        return false;
    }
}
