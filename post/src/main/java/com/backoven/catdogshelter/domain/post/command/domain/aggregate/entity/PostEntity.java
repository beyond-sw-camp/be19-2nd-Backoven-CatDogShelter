package com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity;

/* 자유게시글 입력, 수정, 삭제에 필요한 Entity */


import com.backoven.catdogshelter.common.util.DateTimeUtil;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="post")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostEntity {
    @Id
    //auto_increment 적용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    // DB에 있는 컬럼명과 필드명이 같은 경우 굳이 @Column 쓸 필요가 없음.
    private String title;
    private String content;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;

    // 게시글 삭제시 is_deleted 부분을 1로 표시하여 조회 할 때 게시글이 조회되지 않도록 논리적 삭제 진행
    // 기본값 0은 아직 삭제 안된 상태
    @Column(name="is_deleted")
    private Integer isDeleted = 0;

    // 베타적 관계이므로 래퍼 클래스를 사용해서 null 값을 받아도록 하였음.
    @Column(name="user_id")
    private Integer userId;
    @Column(name="head_id")
    private Integer headId;


    // util 폴더 안 DateTimeUtil 클래스를 통해 현재 시간 표시
    @PrePersist
    public void prePersist() {
        this.createdAt = DateTimeUtil.now();
    }

    // util 폴더 안 DateTimeUtil 클래스를 통해 현재 시간 표시
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = DateTimeUtil.now();
    }

}
