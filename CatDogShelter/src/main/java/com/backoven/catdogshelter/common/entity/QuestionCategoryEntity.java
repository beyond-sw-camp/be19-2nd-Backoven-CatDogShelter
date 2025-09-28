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
@Table(name = "questionCategory")
public class QuestionCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;   // PK (AUTO_INCREMENT)

    @Lob
    @Column(name = "question", nullable = false)
    private String question;  // 지정 질문 (TEXT 컬럼)
}
