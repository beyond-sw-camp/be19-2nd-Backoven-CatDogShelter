package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.UpdateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import com.backoven.catdogshelter.domain.shelteruser.command.domain.aggregate.entity.ShelterUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostCommandService {

    private final DonationPostRepository donationPostRepository;
    // 게시글 등록
    public Long createPost(CreateDonationPostRequest dto) {
        DonationPost post = new DonationPost();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(DateTimeUtil.now());

        // headId → 엔티티 참조
        ShelterUserEntity head = new ShelterUserEntity();
        head.setId(dto.getHeadId()); // PK만 세팅해도 JPA가 프록시로 참조 가능
        post.setHead(head);

        return donationPostRepository.save(post).getId();
    }

    // 게시글 단건 조회
    @Transactional(readOnly = true)
    public DonationPost getPost(Long id) {
        return donationPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DonationPost not found"));
    }

    // 게시글 수정
    @Transactional
    public void updatePost(UpdateDonationPostRequest dto) {
        DonationPost post = getPost(dto.getPostId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(DateTimeUtil.now());
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<DonationPost> getAllPosts() {
        return donationPostRepository.findAll();
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long id, Long headId) {
        DonationPost post = donationPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 작성자 본인인지 확인
        if (post.getHead() == null || !post.getHead().getId().equals(headId)) {
            throw new IllegalStateException("게시글 작성자만 삭제할 수 있습니다.");
        }

        donationPostRepository.delete(post); // Cascade 옵션 덕분에 파일 + 댓글도 같이 삭제됨
    }

    //보호소장 이름으로 조회
    @Transactional(readOnly = true)
    public List<DonationPost> getPostsByCeoName(String ceoName) {
        return donationPostRepository.findByHeadCeoName(ceoName);
    }


}
