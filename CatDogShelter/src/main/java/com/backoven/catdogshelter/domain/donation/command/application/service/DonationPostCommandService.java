package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.UpdateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostCommandService {

    private final DonationPostRepository donationPostRepository;

    public Long createPost(CreateDonationPostRequest dto) {
        DonationPost post = new DonationPost();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(DateTimeUtil.now());
        return donationPostRepository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public DonationPost getPost(Long id) {
        return donationPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DonationPost not found"));
    }

    public void updatePost(UpdateDonationPostRequest dto) {
        DonationPost post = getPost(dto.getPostId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(DateTimeUtil.now());
    }

    public void deletePost(Long id) {
        DonationPost post = getPost(id);
        post.softDelete();
    }
}
