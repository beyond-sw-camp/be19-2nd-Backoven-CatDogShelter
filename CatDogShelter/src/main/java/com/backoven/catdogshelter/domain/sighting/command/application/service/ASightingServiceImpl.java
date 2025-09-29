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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ASightingServiceImpl implements ASightingService {

    private final DSightingService dSightingService;
    private final SightingPostRepository sightingPostRepository;
    private final SightingPostCommentRepository sightingPostCommentRepository;
    private final SightingPostReportRepository sightingPostReportRepository;
    private final SightingPostCommentReportRepository sightingPostCommentReportRepository;
    private final SightingPostFilesRepository sightingPostFilesRepository;
    private final SightingPostLikedRepository sightingPostLikedRepository;
    private final ModelMapper modelMapper;

    @Value("${file.upload-dir}")
    private String imgCommonPath;

    @Autowired
    public ASightingServiceImpl(DSightingService DSightingService, SightingPostRepository sightingPostRepository, SightingPostCommentRepository sightingPostCommentRepository, SightingPostReportRepository sightingPostReportRepository, SightingPostCommentReportRepository sightingPostCommentReportRepository, SightingPostFilesRepository sightingPostFilesRepository, SightingPostLikedRepository sightingPostLikedRepository, ModelMapper modelMapper) {
        this.dSightingService = DSightingService;
        this.sightingPostRepository = sightingPostRepository;
        this.sightingPostCommentRepository = sightingPostCommentRepository;
        this.sightingPostReportRepository = sightingPostReportRepository;
        this.sightingPostCommentReportRepository = sightingPostCommentReportRepository;
        this.sightingPostFilesRepository = sightingPostFilesRepository;
        this.sightingPostLikedRepository = sightingPostLikedRepository;
        this.modelMapper = modelMapper;
    }

    // 게시글 작성
    @Override
    @Transactional
    public int registSightingPost(RequestSightingPostDTO newPostDTO, List<MultipartFile> multiFiles) {
        // 추가하려는 게시글이 규칙을 지키지 않았다면
        dSightingService.validate(newPostDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);  // 정확하게 변수명이 일치하는 것만 매핑
                                                                                        // id 라는 말이 들어가는게 많으므로 설정
        SightingPost newPost = modelMapper.map(newPostDTO, SightingPost.class);

        // createdAt을 서버의 현재시간으로 설정: yyyy-MM-dd HH:mm:ss
        String now = DateTimeUtil.now();
        newPost.setCreatedAt(now);

        // 게시글 insert
        sightingPostRepository.save(newPost);

        if (multiFiles != null) {
            try {
                for(MultipartFile multiFile : multiFiles) {
                    String originFileName = multiFile.getOriginalFilename();
                    String ext = originFileName.substring(originFileName.lastIndexOf("."));
                    String saveName = UUID.randomUUID().toString().replace("-", "") + ext;

                    File dir = new File(imgCommonPath, "Sighting");
                    if(!dir.exists()) {
                        if(!dir.mkdirs()) {
                            // 폴더 만들기 실패 에러
                        }
                    }

                    File dest = new File(dir, saveName);
                    multiFile.transferTo(dest);



                    SightingPostFiles spfile = new SightingPostFiles();
                    spfile.setPostId(newPost.getId());
                    spfile.setFileRename(saveName);
                    spfile.setUploadedAt(now);

                    sightingPostFilesRepository.save(spfile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        // 게시글 번호 반환
        return newPost.getId();
    }

    // 게시글 수정
    @Override
    @Transactional
    public void modifySightingPost(int postId, RequestSightingPostDTO modifyPostDTO, List<MultipartFile> multiFiles) {
        // 변경하려는 게시글이 규칙을 지키지 않았다면
        dSightingService.validate(modifyPostDTO);

        // 엔티티로 변경
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPost modifyPost = modelMapper.map(modifyPostDTO, SightingPost.class);

        // DB에서 정보 불러오기
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        String now = DateTimeUtil.now();
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
            foundPost.setUpdatedAt(now);
        }

        if (multiFiles != null) {
            // 1. 기존 파일 삭제
            List<SightingPostFiles> files = sightingPostFilesRepository.findByPostId(postId);
            for (SightingPostFiles spfile : files) {

                Path path = Paths.get(imgCommonPath,spfile.getFilePath(), spfile.getFileRename());
                if(Files.exists(path)) {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            sightingPostFilesRepository.deleteAll(files);

            // 2. 새로 들어온 파일 저장
            try {
                for(MultipartFile multiFile : multiFiles) {
                    String originFileName = multiFile.getOriginalFilename();
                    String ext = originFileName.substring(originFileName.lastIndexOf("."));
                    String saveName = UUID.randomUUID().toString().replace("-", "") + ext;

                    File dir = new File(imgCommonPath, "Sighting");
                    if(!dir.exists()) {
                        if(!dir.mkdirs()) {
                            // 폴더 만들기 실패 에러
                        }
                    }

                    File dest = new File(dir, saveName);
                    multiFile.transferTo(dest);


                    SightingPostFiles spfile = new SightingPostFiles();
                    spfile.setPostId(postId);
                    spfile.setFileRename(saveName);
                    spfile.setUploadedAt(now);

                    sightingPostFilesRepository.save(spfile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 게시글 삭제
    @Override
    @Transactional
    public boolean removeSightingPost(int postId) {
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

        if(foundPost != null) {
            foundPost.setDeleted(true);

            // 관련 파일들 삭제
            List<SightingPostFiles> files = sightingPostFilesRepository.findByPostId(postId);


            for (SightingPostFiles spfile : files) {

                Path path = Paths.get(imgCommonPath,spfile.getFilePath(), spfile.getFileRename());
                if(Files.exists(path)) {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            sightingPostFilesRepository.deleteAll(files);
            return true;
        }
        return false;
    }

    // 게시글 복원
    @Override
    @Transactional
    public boolean restoreSightingPost(int postId) {
        SightingPost foundPost = sightingPostRepository.findById(postId).orElse(null);

//        log.info("여기까지 옴: {}", foundPost);
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
    @Transactional
    public void registSightingPostReport(RequestSightingPostReportDTO newReportDTO) {
        dSightingService.validate(newReportDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPostReport newReport = modelMapper.map(newReportDTO, SightingPostReport.class);

        newReport.setCreatedAt(DateTimeUtil.now());

        sightingPostReportRepository.save(newReport);
    }

    @Override
    @Transactional
    public void registSightingPostCommentReport(RequestSightingPostCommentReportDTO newReportDTO) {
        dSightingService.validate(newReportDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPostCommentReport newReport = modelMapper.map(newReportDTO, SightingPostCommentReport.class);

        newReport.setCreatedAt(DateTimeUtil.now());

        sightingPostCommentReportRepository.save(newReport);
    }

    @Override
    @Transactional
    public void registSightingPostLiked(RequestSightingPostLikedDTO newLikedDTO) {
        dSightingService.validate(newLikedDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SightingPostLiked newLiked = modelMapper.map(newLikedDTO, SightingPostLiked.class);

        sightingPostLikedRepository.save(newLiked);
    }

    @Override
    @Transactional
    public void deleteSightinPostFile(int postId) {
        List<SightingPostFiles> files = sightingPostFilesRepository.findByPostId(postId);

        for (SightingPostFiles spfile : files) {

            Path path = Paths.get(imgCommonPath,spfile.getFilePath(), spfile.getFileRename());
            if(Files.exists(path)) {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        sightingPostFilesRepository.deleteAll(files);
    }

    @Override
    @Transactional
    public void modifySightingPostReport(int postId, boolean approve) {
        List<SightingPostReport> postReports = sightingPostReportRepository.findByPostId(postId);

        for(SightingPostReport spr : postReports) {
            spr.setStatus(true);
        }
        sightingPostReportRepository.saveAll(postReports);

        // 신고를 승인하면
        if(approve == true) {
            SightingPost post =  sightingPostRepository.findById(postId).orElse(null);
            post.setBlinded(true);
            sightingPostRepository.save(post);
        }
    }

    @Override
    @Transactional
    public void modifySightingPostCommentReport(int commentId, boolean approve) {
        List<SightingPostCommentReport> commentReports = sightingPostCommentReportRepository.findByCommentId(commentId);

        for(SightingPostCommentReport sprc : commentReports) {
            sprc.setStatus(true);
        }
        sightingPostCommentReportRepository.saveAll(commentReports);

        if(approve == true) {
            SightingPostComment comment =  sightingPostCommentRepository.findById(commentId).orElse(null);
            comment.setBlinded(true);
            sightingPostCommentRepository.save(comment);
        }
    }
}
