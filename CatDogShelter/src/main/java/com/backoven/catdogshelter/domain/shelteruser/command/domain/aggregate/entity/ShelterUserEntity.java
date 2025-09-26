package com.backoven.catdogshelter.domain.shelteruser.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelterUserEntity {
    @Id
    @Column(name="head_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ceo_name", nullable = false, length = 20)
    private String userName;
}
