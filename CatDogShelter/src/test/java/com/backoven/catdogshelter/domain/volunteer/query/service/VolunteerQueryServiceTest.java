package com.backoven.catdogshelter.domain.volunteer.query.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class VolunteerQueryServiceTest {

    @Autowired
    VolunteerPostQueryService volunteerPostQueryService;

    @DisplayName("모든 게시글 조회")
    @Test
    void testGetAllVolunteerPosts(){
        Assertions.assertDoesNotThrow(()->{
            volunteerPostQueryService.selectAllVolunteerPosts()
                    .forEach(System.out::println);
        });
    }

    @DisplayName("삭제 나 블라인드가 안된 게시글 모두 조회")
    @Test
    void testGetAllVolunteerPostsByNotBlindedAndDeleted(){
        Assertions.assertDoesNotThrow(
                () -> {
                    volunteerPostQueryService.selectAllVolunteerPostsByNoneProcessed()
                            .forEach(System.out::println);
                }
        );
    }

    @DisplayName("게시글 목록 조회")
    @Test
    void testGetAllVolunteerPost(){
        Assertions.assertDoesNotThrow(
                () -> {

                }
        );
    }
}
