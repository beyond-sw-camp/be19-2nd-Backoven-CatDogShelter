package com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donationPostFiles")
//@Where(clause = "is_deleted = false")
public class DonationPostFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileRename;

    private String filePath;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    @Column(name="uploaded_at")
    private String uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private DonationPost post;

    public void softDelete() {
        this.deleted = true;
    }

}
