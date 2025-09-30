// VolunteerAssociationCommandController.java
package com.backoven.catdogshelter.domain.volunteer.command.application.controller;

import com.backoven.catdogshelter.domain.volunteer.command.application.dto.VolunteerApplyRequest;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.VolunteerApproveRequest;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.VolunteerAssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.VolunteerAssociationUpdateDTO;
import com.backoven.catdogshelter.domain.volunteer.command.application.service.VolunteerAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/association-posts")
public class VolunteerAssociationCommandController {

    private final VolunteerAssociationService service;

    @Autowired
    public VolunteerAssociationCommandController(VolunteerAssociationService service) {
        this.service = service;
    }

    // 작성
    @PostMapping("/write")
    public ResponseEntity<Integer> writeAssociation(@RequestBody VolunteerAssociationDTO dto) {
        Integer id = service.writeAssociation(dto);
        return ResponseEntity.ok(id);
    }

    // 수정
    @PutMapping("/{id}/modify")
    public ResponseEntity<Void> modifyAssociation(@PathVariable Integer id,
                                       @RequestBody VolunteerAssociationUpdateDTO dto) {
        service.modifyAssociation(id, dto);
        return ResponseEntity.ok().build();
    }

    // 삭제 => 마감(soft delete)
//    @DeleteMapping("/{id}/delete")
//    public ResponseEntity<Void> deleteAssociation(@PathVariable Integer id) {
//        service.deleteAssociation(id); // deadline=true
//        return ResponseEntity.ok().build();
//    }

    // 신청
    @PostMapping("/apply")
    public ResponseEntity<Void> apply(@RequestBody VolunteerApplyRequest req) {
        service.apply(req);
        return ResponseEntity.ok().build();
    }

    // 신청 취소
    @DeleteMapping("/apply")
    public ResponseEntity<Void> cancel(@RequestBody VolunteerApplyRequest req) {
        service.cancel(req);
        return ResponseEntity.ok().build();
    }

    // 승인(반려 없음)
    @PostMapping("/apply/approve")
    public ResponseEntity<Void> approve(@RequestBody VolunteerApproveRequest req) {
        service.approve(req);
        return ResponseEntity.ok().build();
    }

    // 종료 처리(선택)
//    @PostMapping("/{id}/end")
//    public ResponseEntity<Void> end(@PathVariable Integer id) {
//        service.endAssociation(id);
//        return ResponseEntity.ok().build();
//    }
}
