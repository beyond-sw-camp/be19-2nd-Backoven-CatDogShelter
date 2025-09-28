package com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.ReportCategory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="donationPostCommentReport")
public class DonationPostCommentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private ReportCategory category;

    @Column(name="etc_detail")
    private String etcDetail;

    @Column(name="created_at")
    private String createdAt;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "comment_id")
    private DonationPostComment comment;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "head_id")
    private ShelterHeadEntity head;
}
