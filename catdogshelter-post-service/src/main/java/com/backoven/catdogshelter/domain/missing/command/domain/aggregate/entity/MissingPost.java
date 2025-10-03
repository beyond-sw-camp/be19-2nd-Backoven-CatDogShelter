package com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity;


import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.SigunguEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "missingPost")
@Where(clause = "is_deleted = false")
public class MissingPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;   // 소프트 삭제 여부

    @Column(name = "is_blinded", nullable = false)
    private boolean blinded = false;   // 관리자/운영자 블라인드 처리 여부


    public void softDelete() {
        this.deleted = true;
        this.updatedAt = DateTimeUtil.now();
    }

    public void blind() {
        this.blinded = true;
        this.updatedAt = DateTimeUtil.now();
    }

    public void unblind() {
        this.blinded = false;
        this.updatedAt = DateTimeUtil.now();
    }

    private Integer view = 0;

    private boolean status;          // 실종 상태 (false=실종, true=발견)

    @Column(name = "detail_address")
    private String detailAddress;    // 실종 상세 위치

    private String gender;           // 성별 (MALE, FEMALE)

    private Integer age;             // 나이

    @Column(name = "registration_num")
    private Integer registrationNum; // 동물등록번호

    private String breed;            // 품종

    @Column(name = "animal_type")
    private String animalType;       // 동물 구분 (CAT, DOG)

    private String color;            // 색상

    private double weight;           // 체중
    @Column(name = "lost_date")

    private String lostDate;         // 실종일자

    private String feature;          // 특징

    private String contact;          // 연락처

    /** ===== 연관관계 ===== **/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserEntity user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ShelterheadEntity head;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sigungu_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SigunguEntity sigungu;



    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissingPostFiles> files = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissingPostComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissingPostLiked> likes = new ArrayList<>();
}
