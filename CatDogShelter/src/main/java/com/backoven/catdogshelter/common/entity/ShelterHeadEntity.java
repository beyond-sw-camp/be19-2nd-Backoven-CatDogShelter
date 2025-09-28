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
@Table(name = "shelter_head")
public class ShelterHeadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "head_id")
    private Integer id;

    @Column(name = "ceo_name", length = 20, nullable = false)
    private String ceoName;

    @Column(name = "ceo_name2", length = 20)
    private String ceoName2;

    @Column(name = "head_account", length = 20, nullable = false, unique = true)
    private String account;

    @Column(name = "head_password", length = 255, nullable = false)
    private String password;

    @Column(name = "e_mail", length = 50, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String answer;

    @Column(name = "head_phone", length = 20, nullable = false, unique = true)
    private String phone;

    @Column(name = "company_name", nullable = false, length = 255)
    private String companyName;

    @Column(name = "biz_number", nullable = false, length = 20)
    private String bizNumber;

    @Column(name = "cor_number", nullable = false, length = 20)
    private String corNumber;

    @Column(name = "company_address", nullable = false, length = 255)
    private String companyAddress;

    @Column(name = "open_date", nullable = false, length = 20)
    private String openDate;

    @Column(name = "close_date", length = 20)
    private String closeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sigungu_id", foreignKey = @ForeignKey(name = "fk_head_sigungu"))
    private SigunguEntity sigungu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionCategory_id", foreignKey = @ForeignKey(name = "fk_user_question"))
    private QuestionCategoryEntity questionCategory;

}
