package com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "noticeLiked")
public class NoticeLikedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 좋아요 대상 공지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false, foreignKey = @ForeignKey(name = "fk_nl_notice"))
    private NoticeEntity notice;

    // 일반 유저 (nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_nl_user"))
    private UserEntity user;

    // 보호소장 (nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id", foreignKey = @ForeignKey(name = "fk_nl_head"))
    private ShelterheadEntity head;

    // user XOR head (둘 중 하나만 설정) 은 서비스에서 보장
}
