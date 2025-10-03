package com.backoven.catdogshelter.domain.volunteerking.query.service;

import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingMonthDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingTotalDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QVolunteerKingServiceImplTest {

    @Autowired
    private QueryVolunteerKingServiceImpl service;

    @DisplayName("월별 봉사왕 조회 테스트")
    @Test
    void findVolunteerKingMonth_shouldReturnList() {
        List<VolunteerKingMonthDTO> result = service.findVolunteerKingMonth();

        assertNotNull(result);
        assertFalse(result.isEmpty(), "월별 봉사왕 리스트가 비어있으면 안됩니다.");

        VolunteerKingMonthDTO first = result.get(0);
        assertTrue(first.getYear() > 0, "년도는 0보다 커야 합니다.");
        assertTrue(first.getMonth() > 0 && first.getMonth() <= 12, "월은 1~12여야 합니다.");
        assertTrue(first.getVolunteerTime() >= 0, "봉사시간은 0 이상이어야 합니다.");
        assertNotNull(first.getUserName(), "사용자 이름은 null일 수 없습니다.");
        assertNotNull(first.getRatingName(), "등급 이름은 null일 수 없습니다.");
    }

    @DisplayName("전체 봉사왕 조회 테스트")
    @Test
    void findVolunteerKingTotal_shouldReturnList() {
        List<VolunteerKingTotalDTO> result = service.findVolunteerKingTotal();

        assertNotNull(result);
        assertFalse(result.isEmpty(), "전체 봉사왕 리스트가 비어있으면 안됩니다.");

        VolunteerKingTotalDTO first = result.get(0);
        assertTrue(first.getVolunteerTime() >= 0, "봉사시간은 0 이상이어야 합니다.");
        assertNotNull(first.getUserName(), "사용자 이름은 null일 수 없습니다.");
        assertNotNull(first.getRatingName(), "등급 이름은 null일 수 없습니다.");

        VolunteerKingTotalDTO king = new VolunteerKingTotalDTO();
        king.setUserName("김하진");//4
        king.setRatingName("댕냥보호천사");//3
        king.setVolunteerTime(150);

        assertEquals(first.getUserName(), king.getUserName());
        assertEquals(first.getRatingName(), king.getRatingName());
        assertEquals(first.getVolunteerTime(), king.getVolunteerTime());
    }

    @DisplayName("특정 사용자 월별 봉사왕 조회 테스트")
    @Test
    void findVolunteerKingByUserId_shouldReturnList() {
        int userId = 1; // 실제 DB에 존재하는 테스트용 userId
        List<VolunteerKingMonthDTO> result = service.findVolunteerKingByUserId(userId);

        assertNotNull(result);
        for (VolunteerKingMonthDTO dto : result) {
            assertNotNull(dto.getUserName(), "사용자 이름은 null일 수 없습니다.");
            assertTrue(dto.getVolunteerTime() >= 0, "봉사시간은 0 이상이어야 합니다.");
            assertNotNull(dto.getRatingName(), "등급 이름은 null일 수 없습니다.");
        }
    }
}
