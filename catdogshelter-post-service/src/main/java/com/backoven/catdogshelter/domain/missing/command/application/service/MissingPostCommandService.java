package com.backoven.catdogshelter.domain.missing.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.SigunguEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.missing.command.application.dto.CreateMissingPostRequest;
import com.backoven.catdogshelter.domain.missing.command.application.dto.UpdateMissingPostRequest;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostFiles;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostCommentRepository;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostFilesRepository;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MissingPostCommandService {

    private final MissingPostRepository missingPostRepository;
    private final MissingPostFilesRepository missingPostFilesRepository;
    private final MissingPostCommentRepository missingCommentRepository;

    private final String uploadDir = "/Users/dong/uploads/";

    // 게시글 단건 조회
    @Transactional(readOnly = true)
    public MissingPost getPost(Integer id) {
        return missingPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MissingPost not found"));
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<MissingPost> getAllPosts() {
        return missingPostRepository.findAll();
    }

    // 보호소장 이름으로 조회
    @Transactional(readOnly = true)
    public List<MissingPost> getPostsByCeoName(String ceoName) {
        return missingPostRepository.findByHeadCeoName(ceoName);
    }

    // 게시글 작성
    @Transactional
    public Integer createMissingPost(CreateMissingPostRequest dto, List<MultipartFile> files) {
        // 1) 게시글 저장
        MissingPost post = new MissingPost();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(DateTimeUtil.now());

        // 작성자: 일반회원 or 보호소장
        if (dto.getUserId() != null) {
            UserEntity user = new UserEntity();
            user.setUserId(dto.getUserId());
            post.setUser(user);
        } else if (dto.getHeadId() != null) {
            ShelterheadEntity head = new ShelterheadEntity();
            head.setId(dto.getHeadId());
            post.setHead(head);
        }

        if (dto.getSigunguId() == null) {
            throw new IllegalArgumentException("sigunguId는 필수 입력값입니다.");
        }
        SigunguEntity sigungu = new SigunguEntity();
        sigungu.setId(dto.getSigunguId());
        post.setSigungu(sigungu);


        post.setStatus(dto.isStatus());
        post.setDetailAddress(dto.getDetailAddress());
        post.setGender(dto.getGender());
        post.setAge(dto.getAge());
        post.setRegistrationNum(dto.getRegistrationNum());
        post.setBreed(dto.getBreed());
        post.setAnimalType(dto.getAnimalType());
        post.setColor(dto.getColor());
        post.setWeight(dto.getWeight());
        post.setLostDate(dto.getLostDate());
        post.setFeature(dto.getFeature());
        post.setContact(dto.getContact());

        missingPostRepository.save(post);

        // 2) 첨부파일 저장
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String original = StringUtils.cleanPath(file.getOriginalFilename());
                String rename = UUID.randomUUID() + "_" + original;

                Path dest = Paths.get(uploadDir, rename);
                try {
                    Files.createDirectories(dest.getParent());
                    file.transferTo(dest.toFile());
                } catch (IOException e) {
                    throw new RuntimeException("파일 저장 실패: " + rename, e);
                }

                MissingPostFiles pf = new MissingPostFiles();
                pf.setFileRename(rename);
                pf.setFilePath("/uploads/" + rename);
                pf.setUploadedAt(DateTimeUtil.now());
                pf.setPost(post);

                missingPostFilesRepository.save(pf);
            }
        }

        return post.getId();
    }

    // 게시글 수정 (작성자 본인만 가능)
    @Transactional
    public void updateMissingPost(UpdateMissingPostRequest dto, List<MultipartFile> files) {
        MissingPost post = missingPostRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        // 작성자 본인 검증
        boolean isOwner = false;
        if (post.getUser() != null && dto.getUserId() != null && post.getUser().getUserId().equals(dto.getUserId())) {
            isOwner = true;
        }
        if (post.getHead() != null && dto.getHeadId() != null && post.getHead().getId().equals(dto.getHeadId())) {
            isOwner = true;
        }
        if (!isOwner) {
            throw new IllegalStateException("게시글 작성자만 수정할 수 있습니다.");
        }

        // 1) 게시글 수정
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(DateTimeUtil.now());

        if (dto.getSigunguId() != null) {
            SigunguEntity sigungu = new SigunguEntity();
            sigungu.setId(dto.getSigunguId());
            post.setSigungu(sigungu);
        }

        post.setStatus(dto.isStatus());
        post.setDetailAddress(dto.getDetailAddress());
        post.setGender(dto.getGender());
        post.setAge(dto.getAge());
        post.setRegistrationNum(dto.getRegistrationNum());
        post.setBreed(dto.getBreed());
        post.setAnimalType(dto.getAnimalType());
        post.setColor(dto.getColor());
        post.setWeight(dto.getWeight());
        post.setLostDate(dto.getLostDate());
        post.setFeature(dto.getFeature());
        post.setContact(dto.getContact());

        // 2) 삭제할 파일 처리
        if (dto.getDeleteFileIds() != null && !dto.getDeleteFileIds().isEmpty()) {
            for (Integer fileId : dto.getDeleteFileIds()) {
                MissingPostFiles file = missingPostFilesRepository.findById(fileId)
                        .orElseThrow(() -> new IllegalArgumentException("삭제할 파일이 존재하지 않습니다."));

                // 해당 게시글에 속한 파일인지 검증
                if (!file.getPost().getId().equals(post.getId())) {
                    throw new IllegalStateException("해당 게시글의 파일이 아닙니다.");
                }

                // 로컬 파일 삭제
                Path path = Paths.get(uploadDir, file.getFileRename());
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new RuntimeException("파일 삭제 실패: " + file.getFileRename(), e);
                }

                // DB에서 삭제
                missingPostFilesRepository.delete(file);
            }
        }

        // 3) 새 파일 추가
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String original = StringUtils.cleanPath(file.getOriginalFilename());
                String rename = UUID.randomUUID() + "_" + original;

                Path dest = Paths.get(uploadDir, rename);
                try {
                    Files.createDirectories(dest.getParent());
                    file.transferTo(dest.toFile());
                } catch (IOException e) {
                    throw new RuntimeException("파일 저장 실패: " + rename, e);
                }

                MissingPostFiles newFile = new MissingPostFiles();
                newFile.setFileRename(rename);
                newFile.setFilePath("/uploads/" + rename);
                newFile.setUploadedAt(DateTimeUtil.now());
                newFile.setPost(post);

                missingPostFilesRepository.save(newFile);
            }
        }
    }

    // 게시글 삭제 (작성자 본인만)
    @Transactional
    public void deleteMissingPost(Integer id, Integer headId, Integer userId) {
        MissingPost post = missingPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 작성자 본인 검증
        boolean isOwner = false;
        if (post.getUser() != null && userId != null && post.getUser().getUserId().equals(userId)) {
            isOwner = true;
        }
        if (post.getHead() != null && headId != null && post.getHead().getId().equals(headId)) {
            isOwner = true;
        }
        if (!isOwner) {
            throw new IllegalStateException("게시글 작성자만 삭제할 수 있습니다.");
        }
        post.softDelete();
    }
}



