package com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.enumeration.AnimalType;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sightingPost")
public class SightingPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "sighted_at", nullable = false)
    private String sightedAt;

    @Column(name = "sighted_place", nullable = false)
    private String sightedPlace;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "animal_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @Column(name = "breed", nullable = false)
    private String breed;

    // insert 시에는 default 값을 활용하고
    // update는 MyBatis로 상세 조회시 트랜잭션으로 일으키게 했으므로 여기서는 불필요
//    @Column(name = "view", nullable = false)
//    private int view;

    @Column(name = "is_blinded", nullable = false)
    private boolean blinded = false;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "head_id")
    private Integer headId;

    @Column(name = "sigungu_id", nullable = false)
    private int sigunguId;
}
