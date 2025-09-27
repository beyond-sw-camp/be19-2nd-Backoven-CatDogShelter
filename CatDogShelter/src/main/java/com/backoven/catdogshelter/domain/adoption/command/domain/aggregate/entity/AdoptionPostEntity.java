package com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity;

import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.AnimalType;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.Gender;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.AdoptionPostStatus;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.YnOption;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "adoptionPost")
public class AdoptionPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", length = 20, nullable = false)
    private String createdAt;

    @Column(name = "updated_at", length = 20)
    private String updatedAt;

    @Column(name = "user_phone", length = 20, nullable = false)
    private String userPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "animal_type", nullable = false)
    private AnimalType animalType;   // CAT, DOG

    @Column(length = 20, nullable = false)
    private String breed;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 20, nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;   // MALE, FEMALE

    @Column(nullable = false)
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdoptionPostStatus status;   // PROTECTING, ADOPTED

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YnOption vaccination;   // Y, N

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YnOption neutering;     // Y, N

    @Column(nullable = false)
    private Integer view = 0;

    @Column(name = "is_blind", nullable = false)
    private Boolean isBlind = false;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // FK 매핑 (User, ShelterHead, Sigungu)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_ap_user"))
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id", foreignKey = @ForeignKey(name = "fk_ap_head"))
    private ShelterHeadEntity shelterHead;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sigungu_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ap_sigungu"))
    private SigunguEntity sigungu;

}
