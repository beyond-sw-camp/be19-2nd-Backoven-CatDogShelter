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
@Table(name = "sigungu")
public class SigunguEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sigungu_id")
    private Integer id;

    @Column(name = "sigungu_code", length = 20)
    private String code;

    @Column(name = "sigungu_name", length = 20)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_id", foreignKey = @ForeignKey(name = "fk_sigungu_sido"))
    private SidoEntity sido;
}
