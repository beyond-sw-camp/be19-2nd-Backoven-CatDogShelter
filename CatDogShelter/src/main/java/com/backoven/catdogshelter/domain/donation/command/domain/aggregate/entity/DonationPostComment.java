package com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="donationPostComment")
//@Where(clause = "is_deleted = false")
public class DonationPostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "is_blinded", nullable = false)
    private boolean blinded = false;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "post_id")
    private DonationPost post;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "head_id")
    private ShelterHeadEntity head;

    public void softDelete() {
        this.deleted = true;
    }

}
