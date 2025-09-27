package com.backoven.catdogshelter.domain.sighting.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.sighting.command.application.dto.*;
import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.*;
import com.backoven.catdogshelter.domain.sighting.command.domain.repository.*;
import com.backoven.catdogshelter.domain.sighting.command.domain.service.DSightingService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ASightingServiceImpl implements ASightingService {

    private final DSightingService dSightingService;
    private final SightingPostRepository sightingPostRepository;
    private final SightingPostCommentRepository sightingPostCommentRepository;
    private final SightingPostReportRepository sightingPostReportRepository;
    private final SightingPostCommentReportRepository sightingPostCommentReportRepository;
    private final SightingPostLikedRepository sightingPostLikedRepository;

    private final ModelMapper modelMapper;
    @Autowired
    public ASightingServiceImpl(DSightingService DSightingService, SightingPostRepository sightingPostRepository, SightingPostCommentRepository sightingPostCommentRepository, SightingPostReportRepository sightingPostReportRepository, SightingPostCommentReportRepository sightingPostCommentReportRepository, SightingPostLikedRepository sightingPostLikedRepository, ModelMapper modelMapper) {
        this.dSightingService = DSightingService;
        this.sightingPostRepository = sightingPostRepository;
        this.sightingPostCommentRepository = sightingPostCommentRepository;
        this.sightingPostReportRepository = sightingPostReportRepository;
        this.sightingPostCommentReportRepository = sightingPostCommentReportRepository;
        this.sightingPostLikedRepository = sightingPostLikedRepository;
        this.modelMapper = modelMapper;
    }

    // 게시글 작성
    @Override
    @Transactional
    public int registSightingPost(RequestSightingPostDTO newPostDTO) {
        // 추가하려는 게시글이 규칙을 지키지 않았다면
        dSightingService.validate(newPostDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);  // 정확하게 변수명이 일치하는 것만 매핑
                                                                                        // id 라는 말이 들어가는게 많으므로 설정
        SightingPost newPost = modelMapper.map(newPostDTO, SightingPost.class);

        // createdAt을 서버의 현재시간으로 설정: yyyy-MM-dd HH:mm:ss
        newPost.setCreatedAt(DateTimeUtil.now());

        // 게시글 insert
        sightingPostRepository.save(newPost);

        // 게시글 번호 반환
        return newPost.getId();
    }

    // 게시글 수정
    @Override
    @Transactional
    public void modifySightingPost(int postId, RequestSightingPostDTO modifyPostDTO) {
        // 변경하려는 게시글이 규칙을 지키지 않았다면
        dSightingService.validate(modifyPostDTO);

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
            foundPost.setUpdatedAt(DateTimeUtil.now());
        }
    }

    // 게시글 삭제
    @Override
    @Transactional
    public boolean removeSightingPost(int postId) {
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        if(foundPost != null) {
            foundPost.setDeleted(true);
            return true;
        }
        return false;
    }

    // 게시글 복원
    @Override
    @Transactional
    public boolean restoreSightingPost(int postId) {
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        log.info("여기까지 옴: {}", foundPost);
        if(foundPost != null) {
            foundPost.setDeleted(false);
            return true;
        }
        return false;
    }

    // 게시글 블라인드
    @Override
    @Transactional
    public boolean blindSightingPost(int postId) {
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        if(foundPost != null) {
            foundPost.setBlinded(true);
            return true;
        }

        return false;
    }

    // 댓글 작성
    @Override
    @Transactional
    public void registSightingPostComment(RequestSightingPostCommentDTO newCommentDTO) {
        dSightingService.validate(newCommentDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPostComment newComment = modelMapper.map(newCommentDTO, SightingPostComment.class);

        // createdAt을 서버의 현재시간으로 설정: yyyy-MM-dd HH:mm:ss
        newComment.setCreatedAt(DateTimeUtil.now());

        // 게시글 insert
        sightingPostCommentRepository.save(newComment);
    }

    // 댓글 수정
    @Override
    @Transactional
    public void modifySightingPostComment(int commentId, RequestSightingPostCommentDTO modifyCommentDTO) {
        dSightingService.validate(modifyCommentDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPostComment newComment = modelMapper.map(modifyCommentDTO, SightingPostComment.class);

        SightingPostComment foundComment = sightingPostCommentRepository.findById(commentId).orElse(null);

        if (foundComment != null) {
            foundComment.setContent(modifyCommentDTO.getContent());
            foundComment.setUpdatedAt(DateTimeUtil.now());
        }
    }

    // 댓글 삭제
    @Override
    @Transactional
    public boolean removeSightingPostComment(int commentId) {
        SightingPostComment foundComment = sightingPostCommentRepository.findById(commentId).orElse(null);

        if(foundComment != null) {
            foundComment.setDeleted(true);
            return true;
        }
        return false;
    }

    // 댓글 복원
    @Override
    @Transactional
    public boolean restoreSightingPostComment(int commentId) {
        SightingPostComment foundComment = sightingPostCommentRepository.findById(commentId).orElse(null);

        if(foundComment != null) {
            foundComment.setDeleted(false);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean blindSightingPostComment(int commentId) {
        SightingPostComment foundComment = sightingPostCommentRepository.findById(commentId).orElse(null);

        if(foundComment != null) {
            foundComment.setBlinded(true);
            return true;
        }
        return false;
    }

    @Override
    public void registSightingPostReport(RequestSightingPostReportDTO newReportDTO) {
        dSightingService.validate(newReportDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPostReport newReport = modelMapper.map(newReportDTO, SightingPostReport.class);

        newReport.setCreatedAt(DateTimeUtil.now());

        sightingPostReportRepository.save(newReport);
    }

    @Override
    public void registSightingPostCommentReport(RequestSightingPostCommentReportDTO newReportDTO) {
        dSightingService.validate(newReportDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPostCommentReport newReport = modelMapper.map(newReportDTO, SightingPostCommentReport.class);

        newReport.setCreatedAt(DateTimeUtil.now());

        sightingPostCommentReportRepository.save(newReport);
    }

    @Override
    public void registSightingPostLiked(RequestSightingPostLikedDTO newLikedDTO) {
        dSightingService.validate(newLikedDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPostLiked newLiked = modelMapper.map(newLikedDTO, SightingPostLiked.class);

        sightingPostLikedRepository.save(newLiked);
    }
}
