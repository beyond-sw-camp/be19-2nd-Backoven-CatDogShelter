// VolunteerAssociationService.java
package com.backoven.catdogshelter.domain.volunteer.command.application.service;

import com.backoven.catdogshelter.domain.volunteer.command.application.dto.VolunteerApplyRequest;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.VolunteerApproveRequest;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.VolunteerAssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.VolunteerAssociationUpdateDTO;

public interface VolunteerAssociationService {

    Integer writeAssociation(VolunteerAssociationDTO dto);

    void modifyAssociation(Integer id, VolunteerAssociationUpdateDTO dto);

    /** 물리 삭제 대신 마감(soft delete) 처리 */
    void deleteAssociation(Integer id);

//    /** 일반회원 신청 */
    Integer apply(VolunteerApplyRequest req);

//    /** 일반회원 신청 취소 */
    void cancel(VolunteerApplyRequest req);

//    /** 보호소장 승인만 존재(반려 없음) */
    void approve(VolunteerApproveRequest req);

//    /** 모임 종료 처리 (is_end = true) – 필요 시 유지 */
    void endAssociation(Integer id);
}
