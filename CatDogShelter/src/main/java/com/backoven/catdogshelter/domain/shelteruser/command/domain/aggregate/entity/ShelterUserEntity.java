package com.backoven.catdogshelter.domain.shelteruser.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shelter_head")
public class ShelterUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "head_id")
    private Long id;

    @Column(name = "ceo_name", nullable = false, length = 20)
    private String ceoName;
}
