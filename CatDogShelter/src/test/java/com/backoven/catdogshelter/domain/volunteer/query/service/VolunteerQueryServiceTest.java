package com.backoven.catdogshelter.domain.volunteer.query.service;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerPostDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class VolunteerQueryServiceTest {

    @Autowired
    VolunteerPostQueryService volunteerPostQueryService;

    @DisplayName("모든 게시글 조회")
    @Test
    void testGetAllVolunteerPosts(){
        Assertions.assertDoesNotThrow(()->{
            volunteerPostQueryService.getVolunteerPostList()
                    .forEach(System.out::println);
        });
    }

}
