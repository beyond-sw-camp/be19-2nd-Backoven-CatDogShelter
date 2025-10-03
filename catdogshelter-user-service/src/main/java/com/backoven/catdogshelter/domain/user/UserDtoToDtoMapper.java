package com.backoven.catdogshelter.domain.user;

import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestRegistUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.user.UserDTO;

// dto끼리의 매핑
public class UserDtoToDtoMapper {

    // RequestRegistUserDTO -> UserDTO 변환
    public static UserDTO toUserDTO(RequestRegistUserDTO request) {
        return UserDTO.builder()
                .userId(null) // PK는 아직 없음
                .userName(request.getUserName())
                .userAccount(request.getUserAccount())
                .userPassword(request.getUserPassword())
                .email(request.getEmail())
                .detailAddress(request.getDetailAddress())
                .answer(request.getAnswer())
                .userPhone(request.getUserPhone())

                // 사용자가 입력하지 않는 값은 기본 0으로 세팅
                .cumulativeVolunteerTime(0)
                .monthVolunteerTime(0)
                .volunteerCount(0)
                .deactivationTimes(0)

                // 회원가입 시 기본 상태는 "GENERAL"
                .userStatus("GENERAL")
                .activationDate(null)
                .ratingId(0)
                .sigunguId(request.getSigunguId())
                .questionCategoryId(request.getQuestionCategoryId())
                .build();
    }
    // RequestModifyUserDTO -> UserDTO 변환
    public static UserDTO toUserDTO(RequestModifyUserDTO request, UserDTO existingUser) {
        return UserDTO.builder()
                .userId(existingUser.getUserId()) // 기존 PK 유지
                .userName(existingUser.getUserName()) // 이름은 수정 X
                .userAccount(existingUser.getUserAccount()) // 계정도 수정 X
                .userPassword(existingUser.getUserPassword()) // 비밀번호는 별도 API에서만 수정

                .email(existingUser.getEmail()) // 이메일은 수정 X
                .userPhone(existingUser.getUserPhone()) // 전화번호도 수정 X

                // 수정 가능 필드
                .detailAddress(request.getDetailAddress()) // 상세 주소
                .sigunguId(request.getSigunguId()) // 시군구
                .questionCategoryId(request.getQuestionCategoryId()) // 질문
                .answer(request.getAnswer()) // 질문 답변

                // 그대로 유지할 값
                .cumulativeVolunteerTime(existingUser.getCumulativeVolunteerTime())
                .monthVolunteerTime(existingUser.getMonthVolunteerTime())
                .volunteerCount(existingUser.getVolunteerCount())
                .deactivationTimes(existingUser.getDeactivationTimes())
                .userStatus(existingUser.getUserStatus())
                .activationDate(existingUser.getActivationDate())
                .ratingId(existingUser.getRatingId())
                .build();
    }
}