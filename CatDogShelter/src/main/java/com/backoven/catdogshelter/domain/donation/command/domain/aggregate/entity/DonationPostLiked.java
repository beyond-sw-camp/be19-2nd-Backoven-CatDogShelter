package com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity;

import com.backoven.catdogshelter.domain.shelteruser.command.domain.aggregate.entity.ShelterUserEntity;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "donationPostLiked",  uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id", "head_id"}))
//@Where(clause = "is_deleted = false")
public class DonationPostLiked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "post_id")
    private DonationPost post;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "head_id")
    private ShelterUserEntity head;
}
