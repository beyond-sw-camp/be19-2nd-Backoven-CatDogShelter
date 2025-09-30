package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.Data;

@Data
public class VolunteerAssociationDTO {
    private int id;                 // 번호
    private String title;           // 제목
    private String content;         // 내용
    private String createdAt;       // 봉사모임(활동) 공고 게시일자
    private int time;               // 봉사 시간
    private String detailAddress;   // 상세주소
    private boolean deadline;       // 마감여부
    private boolean end;            // 봉사종료여부
    private int numberOfPeople;     // 모집인원
    private String startDate;       // 봉사활동 일자
    private String companyName;     // 보호소 이름
}
