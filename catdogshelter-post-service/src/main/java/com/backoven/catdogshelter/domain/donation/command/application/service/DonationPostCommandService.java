package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.UpdateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostFiles;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostFilesRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import lombok.RequiredArgsConstructor;
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
public class DonationPostCommandService {

    private final DonationPostRepository donationPostRepository;
    private final DonationPostFilesRepository donationPostFilesRepository;
    private final String uploadDir = "/Users/haeone/Desktop/be19-2nd-backoven-petShelter/uploads/";
//    private final String uploadDir = "/Users/dong/uploads/";

    // 게시글 단건 조회
    @Transactional(readOnly = true)
    public DonationPost getPost(Integer id) {
        return donationPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
    }


    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<DonationPost> getAllPosts() {
        return donationPostRepository.findAll();
    }

    //보호소장 이름으로 조회
    @Transactional(readOnly = true)
    public List<DonationPost> getPostsByCeoName(String ceoName) {
        return donationPostRepository.findByHeadCeoName(ceoName);
    }






    //게시글 작성(보호소장만) 제목, 내용, 파일첨부
    @Transactional
    public Integer createDonationPost(CreateDonationPostRequest dto, List<MultipartFile> files) {
        // 1) 게시글 저장
        DonationPost post = new DonationPost();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(DateTimeUtil.now());

        // head: 조회 없이 id만 세팅 (FK만 연결됨)
        ShelterheadEntity head = new ShelterheadEntity();
        head.setId(dto.getHeadId());
        post.setHead(head);

        donationPostRepository.save(post);

        // 2) 첨부파일 저장
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String original = StringUtils.cleanPath(file.getOriginalFilename());
                String rename = UUID.randomUUID() + "_" + original;

                Path dest = Paths.get(uploadDir, rename);
                try {
                    Files.createDirectories(dest.getParent()); // 부모 디렉토리 없으면 생성
                    file.transferTo(dest.toFile());           // 파일 저장
                } catch (IOException e) {
                    throw new RuntimeException("파일 저장 실패: " + rename, e);
                }

                DonationPostFiles pf = new DonationPostFiles();
                pf.setFileRename(rename);
                pf.setFilePath("/uploads/" + rename);     // URL 접근용 경로
                pf.setUploadedAt(DateTimeUtil.now());
                pf.setPost(post);

                donationPostFilesRepository.save(pf);
            }
        }

        return post.getId();
    }



    //게시글 수정(작성자 본인만 가능) 파일첨부&삭제
    @Transactional
    public void updateDonationPost(UpdateDonationPostRequest dto, Integer headId, List<MultipartFile> files) {
        DonationPost post = donationPostRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 작성자 본인 검증
        if (post.getHead() == null || !post.getHead().getId().equals(headId)) {
            throw new IllegalStateException("게시글 작성자만 수정할 수 있습니다.");
        }

        // 1) 게시글 수정
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(DateTimeUtil.now());

        // 2) 삭제할 파일 처리
        if (dto.getDeleteFileIds() != null && !dto.getDeleteFileIds().isEmpty()) {
            for (Integer fileId : dto.getDeleteFileIds()) {
                DonationPostFiles file = donationPostFilesRepository.findById(fileId)
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
                donationPostFilesRepository.delete(file);
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

                DonationPostFiles newFile = new DonationPostFiles();
                newFile.setFileRename(rename);
                newFile.setFilePath("/uploads/" + rename);
                newFile.setUploadedAt(DateTimeUtil.now());
                newFile.setPost(post);

                donationPostFilesRepository.save(newFile);
            }
        }
    }

    // 게시글 삭제(작성자 본인만)
    @Transactional
    public void deleteDonationPost(Integer id, Integer headId) {
        DonationPost post = donationPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 작성자 본인인지 확인
        if (post.getHead() == null || !post.getHead().getId().equals(headId)) {
            throw new IllegalStateException("게시글 작성자만 삭제할 수 있습니다.");
        }

        donationPostRepository.delete(post);
    }

}
