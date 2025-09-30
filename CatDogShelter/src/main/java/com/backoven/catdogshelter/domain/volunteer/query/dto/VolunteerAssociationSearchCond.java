package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class VolunteerAssociationSearchCond {
    // 기본 필터
    private Integer sigunguId;       // 지역 필터
    private Integer headId;          // 보호소장 필터
    private Boolean deadline;        // 마감여부
    private Boolean isEnd;           // 종료여부

    // 텍스트 검색 (제목/내용)
    private String keyword;

    // 날짜 범위 (문자열로 저장되어 있으므로 'YYYY-MM-DD' 같은 형식 가정)
    private String startDateFrom;    // 시작일자 >=
    private String startDateTo;      // 시작일자 <=

    // 정렬/페이징
    private String orderBy;          // "createdAt", "startDate", "time" 등 화이트리스트에서만 허용
    private String orderDir;         // "ASC" | "DESC"
    private Integer limit;           // 페이지 크기
    private Integer offset;          // 시작 인덱스
}
