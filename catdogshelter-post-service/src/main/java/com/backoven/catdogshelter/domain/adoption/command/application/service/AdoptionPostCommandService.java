package com.backoven.catdogshelter.domain.adoption.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.SigunguEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.adoption.command.application.dto.*;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.*;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.ReportCategory;
import com.backoven.catdogshelter.domain.adoption.command.domain.repository.AdoptionPostFileRepository;
import com.backoven.catdogshelter.domain.adoption.command.domain.repository.AdoptionPostLikedRepository;
import com.backoven.catdogshelter.domain.adoption.command.domain.repository.AdoptionPostRepository;
import com.backoven.catdogshelter.domain.adoption.command.domain.repository.AdoptionShelterHeadRepository;
import com.backoven.catdogshelter.common.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AdoptionPostCommandService {
    private final AdoptionPostRepository adoptionPostRepository;
    private final AdoptionPostFileRepository adoptionPostFileRepository;
    private final AdoptionPostLikedRepository adoptionPostLikedRepository;
    private final UserRepository userRepository;
    private final AdoptionShelterHeadRepository headRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdoptionPostCommandService(
            AdoptionPostRepository adoptionPostRepository,
            AdoptionPostFileRepository adoptionPostFileRepository,
            AdoptionPostLikedRepository adoptionPostLikedRepository,
            UserRepository userRepository,
            AdoptionShelterHeadRepository headRepository,
            ModelMapper modelMapper) {
        this.adoptionPostRepository = adoptionPostRepository;
        this.adoptionPostFileRepository = adoptionPostFileRepository;
        this.adoptionPostLikedRepository = adoptionPostLikedRepository;
        this.userRepository = userRepository;
        this.headRepository = headRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Boolean updateAdoptionPostLiked(int postId, Integer userId, Integer headId) {
        AdoptionPostEntity post = adoptionPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        if (userId != null) {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

            if (adoptionPostLikedRepository.existsByAdoptionPostAndUser(post, user)) {
                adoptionPostLikedRepository.deleteByAdoptionPostAndUser(post, user);
                return false;
            } else {
                AdoptionPostLikedEntity liked = AdoptionPostLikedEntity.builder()
                        .adoptionPost(post)
                        .user(user)
                        .build();
                adoptionPostLikedRepository.save(liked);
                return true;
            }
        }

        if (headId != null) {
            ShelterheadEntity head = headRepository.findById(headId)
                    .orElseThrow(() -> new IllegalArgumentException("보호소장 없음"));

            if (adoptionPostLikedRepository.existsByAdoptionPostAndShelterHead(post, head)) {
                adoptionPostLikedRepository.deleteByAdoptionPostAndShelterHead(post, head);
                return false;
            } else {
                AdoptionPostLikedEntity liked = AdoptionPostLikedEntity.builder()
                        .adoptionPost(post)
                        .shelterHead(head)
                        .build();
                adoptionPostLikedRepository.save(liked);
                return true;
            }
        }
        return false;//?
    }
    @Value("${file.upload-dir}")
    private String uploadDir;

    @SneakyThrows
    @Transactional
    public void registAdoptionPost(AdoptionPostCommandDTO newPost) throws IOException {
        AdoptionPostEntity postEntity = new AdoptionPostEntity();

        // 기본 정보
        postEntity.setTitle(newPost.getTitle());
        postEntity.setContent(newPost.getContent());
        postEntity.setCreatedAt(DateTimeUtil.now());
        postEntity.setUpdatedAt(DateTimeUtil.now());
        postEntity.setUserPhone(newPost.getUserPhone());

        // 동물 정보
        postEntity.setAnimalType(newPost.getAnimalType());
        postEntity.setBreed(newPost.getBreed());
        postEntity.setAge(newPost.getAge());
        postEntity.setColor(newPost.getColor());
        postEntity.setGender(newPost.getGender());
        postEntity.setWeight(newPost.getWeight());

        // 상태 정보
        postEntity.setStatus(newPost.getStatus());
        postEntity.setVaccination(newPost.getVaccination());
        postEntity.setNeutering(newPost.getNeutering());

        // FK 매핑
        if (newPost.getUserId() != null) {
            UserEntity user = new UserEntity();
            user.setUserId(newPost.getUserId());
            postEntity.setUser(user);
        }
        if (newPost.getHeadId() != null) {
            ShelterheadEntity head = new ShelterheadEntity();
            head.setId(newPost.getHeadId());
            postEntity.setShelterHead(head);
        }
        if (newPost.getSigunguId() != null) {
            SigunguEntity sigungu = new SigunguEntity();
            sigungu.setId(newPost.getSigunguId());
            postEntity.setSigungu(sigungu);
        }

        // 파일 저장
        List<AdoptionPostFileEntity> fileEntities = new ArrayList<>();
        if (newPost.getFiles() != null) {
            for (MultipartFile file : newPost.getFiles()) {
                if (!file.isEmpty()) {
                    String originalName = file.getOriginalFilename();
                    String ext = originalName.substring(originalName.lastIndexOf("."));
                    String storedName = UUID.randomUUID() + ext;

                    File dest = new File(uploadDir, storedName);
                    dest.getParentFile().mkdirs();
                    file.transferTo(dest);

                    AdoptionPostFileEntity fileEntity = new AdoptionPostFileEntity();
                    fileEntity.setFileRename(storedName);
                    fileEntity.setFilePath(dest.getAbsolutePath());
                    fileEntity.setUploadedAt(DateTimeUtil.now());
                    fileEntity.setAdoptionPost(postEntity);
                    fileEntities.add(fileEntity);

                    fileEntity.setAdoptionPost(postEntity);
                    fileEntities.add(fileEntity);
                }
            }
        }
        postEntity.setFiles(fileEntities);   // 부모 쪽에 전체 리스트 세팅
        adoptionPostRepository.save(postEntity);
    }

    /*
    @Transactional
    public void insertAdoptionPostView(int postId) {
        AdoptionPostEntity adoptionPostEntity =
                adoptionPostRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        adoptionPostEntity.setView(adoptionPostEntity.getView()+1);

    }*/


    // 게시글 수정
    public void modifyAdoptionPost(int postId, AdoptionPostCommandDTO modifyPost) throws IOException {
        AdoptionPostEntity foundPost = adoptionPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        // ====== 게시글 기본 정보 수정 ======
        foundPost.setTitle(modifyPost.getTitle());
        foundPost.setContent(modifyPost.getContent());
        foundPost.setAnimalType(modifyPost.getAnimalType());
        foundPost.setAge(modifyPost.getAge());
        foundPost.setColor(modifyPost.getColor());
        foundPost.setGender(modifyPost.getGender());
        foundPost.setWeight(modifyPost.getWeight());
        foundPost.setVaccination(modifyPost.getVaccination());
        foundPost.setNeutering(modifyPost.getNeutering());
        foundPost.setStatus(modifyPost.getStatus());
        foundPost.setUpdatedAt(DateTimeUtil.now());

        // ====== 기존 파일 삭제 ======
        if (foundPost.getFiles() != null) {
            adoptionPostFileRepository.deleteAll(foundPost.getFiles()); // DB에서 삭제
            foundPost.getFiles().clear(); // 메모리에서 제거
        }

        // ====== 새 파일 업로드 ======
        if (modifyPost.getFiles() != null && !modifyPost.getFiles().isEmpty()) {
            for (MultipartFile file : modifyPost.getFiles()) {
                if (!file.isEmpty()) {
                    String originalName = file.getOriginalFilename();
                    String ext = originalName.substring(originalName.lastIndexOf("."));
                    String storedName = UUID.randomUUID() + ext;

                    File dest = new File(uploadDir, storedName);
                    dest.getParentFile().mkdirs();
                    file.transferTo(dest);

                    AdoptionPostFileEntity fileEntity = new AdoptionPostFileEntity();
                    fileEntity.setFileRename(storedName);
                    fileEntity.setFilePath(dest.getAbsolutePath());
                    fileEntity.setUploadedAt(DateTimeUtil.now());
                    fileEntity.setAdoptionPost(foundPost);

                    // Hibernate가 관리하는 컬렉션에 직접 추가
                    foundPost.getFiles().add(fileEntity);
                }
            }
        }
        adoptionPostRepository.save(foundPost);
    }

    @Transactional
    public void deleteAdoptionPost(int deletePostId) {
        AdoptionPostEntity foundPost =
                adoptionPostRepository.findById(deletePostId)
                        .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        foundPost.setIsBlind(true);

    }

    @Transactional
    public void registAdoptionPostComment(int postId, AdoptionPostCommentDTO newCommentDto) {
        AdoptionPostEntity foundPost =
                adoptionPostRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        // 새 댓글 엔티티 생성
        AdoptionPostCommentEntity commentEntity = new AdoptionPostCommentEntity();
        commentEntity.setContent(newCommentDto.getContent());
        commentEntity.setCreatedAt(DateTimeUtil.now());
        commentEntity.setUpdatedAt(DateTimeUtil.now());

        // User 매핑
        if (newCommentDto.getUserId() != null) {
            UserEntity user = new UserEntity();
            user.setUserId(newCommentDto.getUserId());
            commentEntity.setUser(user);
        }

        // ShelterHead 매핑
        if (newCommentDto.getHeadId() != null) {
            ShelterheadEntity head = new ShelterheadEntity();
            head.setId(newCommentDto.getHeadId());
            commentEntity.setShelterHead(head);
        }

        // 게시글과 연결
        commentEntity.setAdoptionPost(foundPost);

        // 기존 댓글 리스트에 추가
        foundPost.getComments().add(commentEntity);

        // 저장 (cascade = ALL이면 댓글도 같이 저장됨)
        adoptionPostRepository.save(foundPost);
    }
    @Transactional
    public void modifyAdoptionPostComment(int postId, int commentId, AdoptionPostCommentDTO adoptionPostComment) {
        AdoptionPostEntity foundPost = adoptionPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        AdoptionPostCommentEntity foundComment = foundPost.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));
        foundComment.setContent(adoptionPostComment.getContent());
        foundComment.setUpdatedAt(DateTimeUtil.now());
    }

    @Transactional
    public void deleteAdoptionPostComment(int postId, int commentId) {
        AdoptionPostEntity foundPost =
                adoptionPostRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        AdoptionPostCommentEntity foundComment = foundPost.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));
        foundComment.setIsBlinded(true);
    }

    @Transactional
    public void registAdoptionPostReport(
            int postId, AdoptionPostReportDTO adoptionPostReportDTO) {
        try { AdoptionPostEntity foundPost =
                adoptionPostRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        AdoptionPostReportEntity adoptionPostReportEntity = new AdoptionPostReportEntity();
        adoptionPostReportEntity.setCategory(ReportCategory.valueOf(adoptionPostReportDTO.getCategory()));
        adoptionPostReportEntity.setEtcDetail(adoptionPostReportDTO.getEtcDetail());
        adoptionPostReportEntity.setCreatedAt(DateTimeUtil.now());

        if (adoptionPostReportDTO.getUserId() != null) {
            UserEntity user = new UserEntity();
            user.setUserId(adoptionPostReportDTO.getUserId());
            adoptionPostReportEntity.setUser(user);
        }

        if (adoptionPostReportDTO.getHeadId() != null) {
            ShelterheadEntity head = new ShelterheadEntity();
            head.setId(adoptionPostReportDTO.getHeadId());
            adoptionPostReportEntity.setShelterHead(head);
        }
        adoptionPostReportEntity.setAdoptionPost(foundPost);

        foundPost.getReports().add(adoptionPostReportEntity);
        adoptionPostRepository.save(foundPost);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("이미 신고한 게시글입니다.", e);
        }
    }

    @Transactional
    public void registAdoptionPostCommentReport(
            int postId, int commentId, AdoptionPostCommentReportDTO dto) {

        try {// 1. 게시글 조회
        AdoptionPostEntity post = adoptionPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        // 2. 게시글 안에서 댓글 찾기
        AdoptionPostCommentEntity comment = post.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        // 3. 신고 엔티티 생성
        AdoptionPostCommentReportEntity report = new AdoptionPostCommentReportEntity();
        report.setCategory(ReportCategory.valueOf(dto.getCategory()));
        report.setEtcDetail(dto.getEtcDetail());
        report.setCreatedAt(dto.getCreatedAt());
        report.setComment(comment);

        // 4. 신고자 매핑
        if (dto.getUserId() != null) {
            UserEntity user = new UserEntity();
            user.setUserId(dto.getUserId());
            report.setUser(user);
        }
        if (dto.getHeadId() != null) {
            ShelterheadEntity head = new ShelterheadEntity();
            head.setId(dto.getHeadId());
            report.setShelterHead(head);
        }

        // 5. 댓글에 신고 추가 (양방향 매핑)
        comment.getReports().add(report);

        // 6. 저장 (cascade 설정이 되어있다면 comment → report도 함께 저장됨)
        adoptionPostRepository.save(post);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("이미 신고한 댓글입니다.", e);
        }
    }

}
