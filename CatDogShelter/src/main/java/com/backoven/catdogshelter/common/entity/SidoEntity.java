package com.backoven.catdogshelter.common.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "sido")
public class SidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sido_id")
    private Integer id;

    @Column(name = "sido_code", length = 20, nullable = false)
    private String code;

    @Column(name = "sido_name", length = 20, nullable = false)
    private String name;
}
