package com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="donationPostCommentReport",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"comment_id", "user_id"}),
                @UniqueConstraint(columnNames = {"comment_id", "head_id"})
        })
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

    @Column(nullable = false)
    private boolean status = false;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "comment_id")
    private DonationPostComment comment;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "head_id")
    private ShelterheadEntity head;
}
