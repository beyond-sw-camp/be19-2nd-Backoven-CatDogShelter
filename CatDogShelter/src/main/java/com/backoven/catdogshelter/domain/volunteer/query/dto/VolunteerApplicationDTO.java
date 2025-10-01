// src/main/java/com/backoven/catdogshelter/domain/volunteer/query/dto/VolunteerApplicationDTO.java
package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class VolunteerApplicationDTO {
    private Integer id;          // 신청내역 PK
    private Boolean status;      // 승인 여부
    private Integer volunteerId; // volunteerAssociation.id
    private Integer userId;      // user.user_id
    private String userName;     // user.user_name
}
