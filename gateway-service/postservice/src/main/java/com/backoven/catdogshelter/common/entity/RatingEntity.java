package com.backoven.catdogshelter.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "rating")
public class RatingEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "standard", nullable = false)
    private Integer standard;
}
