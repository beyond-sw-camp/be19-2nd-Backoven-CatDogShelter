package com.backoven.catdogshelter.common.entity;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.enumeration.UserStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")   // PK
    private Integer userId;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName; // 회원명

    @Column(name = "user_account", nullable = false, length = 20, unique = true)
    private String userAccount; // 아이디

    @Column(name = "user_password", nullable = false, length = 255)
    private String encryptPwd; // 비밀번호 (암호화된 값)

    @Column(name = "e_mail", nullable = false, length = 50, unique = true)
    private String email; // 이메일

    @Column(name = "detail_address")
    private String detailAddress; // 상세주소

    @Column(name = "answer", nullable = false, length = 255)
    private String answer; // 지정답변

    @Column(name = "user_phone", nullable = false, length = 20, unique = true)
    private String userPhone; // 전화번호

    @Column(name = "cumulative_volunteer_time", nullable = false)
    private Integer cumulativeVolunteerTime; // 누적봉사시간

    @Column(name = "month_volunteer_time")
    private Integer monthVolunteerTime; // 월봉사시간

    @Column(name = "volunteer_count", nullable = false)
    private Integer volunteerCount; // 봉사횟수

    @Column(name = "deactivation_times", nullable = false)
    private Integer deactivationTimes; // 블라인드 횟수

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false, length = 10)
    private UserStatus userStatus; // 회원상태 (GENERAL, BLACK, CANCEL)

    @Column(name = "activation_date", length = 20)
    private String activationDate; // 정지 해제일

    /* FK 매핑 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_id", nullable = false)
    private RatingEntity rating; // 회원 등급

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sigungu_id", nullable = false)
    private SigunguEntity sigungu; // 소속 시군구

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionCategory_id", nullable = false)
    private QuestionCategoryEntity questionCategory; // 지정질문 카테고리
}
