package com.backoven.catdogshelter.domain.message.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestMessageSummaryDTO {
    private Integer userId = null;
    private Integer headId = null;
}
