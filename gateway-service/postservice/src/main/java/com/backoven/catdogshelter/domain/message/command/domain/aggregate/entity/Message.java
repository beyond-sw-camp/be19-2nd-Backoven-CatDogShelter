package com.backoven.catdogshelter.domain.message.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "subject_number")
    private int subjectNumber;

    @Column(name = "subject_user_id")
    private Integer subjectUserId;

    @Column(name = "send_user_id")
    private Integer sendUserId;

    @Column(name = "subject_head_id")
    private Integer subjectHeadId;

    @Column(name = "send_head_id")
    private Integer sendHeadId;
}
