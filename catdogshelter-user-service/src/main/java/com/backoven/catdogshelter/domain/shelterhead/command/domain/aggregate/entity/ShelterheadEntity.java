package com.backoven.catdogshelter.domain.shelterhead.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="shelter_head")
public class ShelterheadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="head_id")
    private int headId;

    @Column(name="ceo_name")
    private String ceoName;

    @Column(name="ceo_name2")
    private String ceoName2;

    @Column(name="e_mail")
    private String email;

    @Column(name="head_password", nullable = false)
    private String headPassword;

    @Column(name="head_phone")
    private String headPhone;

    @Column(name="company_name")
    private String companyName;

    @Column(name="biz_number")
    private String bizNumber;

    @Column(name="cor_number")
    private String corNumber;

    @Column(name="company_address")
    private String companyAddress;

    @Column(name="open_date")
    private String openDate;

    private String answer;

    @Column(name="close_date")
    private String closeDate;

    @Column(name="sigungu_id")
    private String sigunguId;

    @Column(name="questionCategory_id")
    private String questionCategoryId;

    // 회원 가입 시 생성 될 고유 아이디(닉네임 개념). 테이블 컬럼명 하나를 사용해야하기 때문에 아이디 부분 사용
    @Column(name="head_account")
    private String headAccount;


}
