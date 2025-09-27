package com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.util.ReportCategory;
import com.backoven.catdogshelter.domain.shelteruser.command.domain.aggregate.entity.ShelterUserEntity;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donationPostReport")
public class DonationPostReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportCategory category;   // 신고 카테고리

    @Column(name="etc_detail")
    private String etcDetail;
    // 신고 세부 사유

    @Column(name="created_at")
    private String createdAt;
    private boolean status = false;    // 처리 여부 (기본 false)

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "post_id")
    private DonationPost post;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "head_id")
    private ShelterUserEntity head;
}
