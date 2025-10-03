package com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity;//package com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity;
//
//import com.backoven.catdogshelter.common.entity.QuestionCategoryEntity;
//import com.backoven.catdogshelter.common.entity.RatingEntity;
//import com.backoven.catdogshelter.common.entity.SigunguEntity;
//import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.AdoptionPostStatus;
//import jakarta.persistence.*;
//import jakarta.persistence.Entity;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@Entity
//@Table(name = "user")
//public class UserEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private Integer id;
//
//    @Column(name = "user_name", length = 20, nullable = false)
//    private String name;
//
//    @Column(name = "user_account", length = 20, nullable = false, unique = true)
//    private String account;
//
//    @Column(name = "user_password", length = 255, nullable = false)
//    private String password;
//
//    @Column(name = "e_mail", length = 50, nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "detail_address")
//    private String detailAddress;
//
//    @Column(nullable = false)
//    private String answer;
//
//    @Column(name = "user_phone", length = 20, nullable = false, unique = true)
//    private String phone;
//
//    private Integer cumulativeVolunteerTime;
//    private Integer monthVolunteerTime;
//    private Integer volunteerCount;
//    private Integer deactivationTimes;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "user_status", nullable = false)
//    private AdoptionPostStatus status;
//
//    @Column(name = "activation_date", nullable = false, length = 20)
//    private String activationDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sigungu_id", foreignKey = @ForeignKey(name = "fk_user_sigungu"))
//    private SigunguEntity sigungu;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "rating_id", foreignKey = @ForeignKey(name = "fk_user_rating"))
//    private RatingEntity rating;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "questionCategory_id", foreignKey = @ForeignKey(name = "fk_user_question"))
//    private QuestionCategoryEntity questionCategory;
//
//}
//
