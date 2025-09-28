package com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "loginHistory")
@Entity
public class LoginHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ip_address", nullable = false, length = 39)
    private String ipAddress;

    @Column(name = "logged_at", nullable = false, length = 20)
    private String loggedAt;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "head_id")
    private Integer headId;
}
