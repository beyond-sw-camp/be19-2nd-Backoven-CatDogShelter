package com.backoven.catdogshelter.domain.sighting.command.application.service;


import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostDTO;
import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPost;
import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.enumeration.AnimalType;
import com.backoven.catdogshelter.domain.sighting.command.domain.repository.SightingPostRepository;
import com.backoven.catdogshelter.domain.sighting.command.domain.service.DSightingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ASightingServiceImplTest {
    @Autowired
    private ASightingServiceImpl service;

    @Qualifier("DSightingServiceImpl")
    @Autowired
    private DSightingService dSightingService;

    @Autowired
    private SightingPostRepository sightingPostRepository;

    private int postId2;

    // 게시글 삽입 테스트
    @DisplayName("목격 정보 게시글 삽입 테스트")
    @Test
    void registSightingPost_shouldSavePost() {
        RequestSightingPostDTO dto = new RequestSightingPostDTO();
        dto.setTitle("테스트 제목");
        dto.setContent("테스트 내용");
        dto.setSightedPlace("역삼역");
        dto.setSightedAt("2025.06.12 08:30:00");
        dto.setAnimalType(AnimalType.CAT);
        dto.setBreed("고양이");
        dto.setColor("블랙");
        dto.setSigunguId(3);
        dto.setUserId(3);

        int postId = service.registSightingPost(dto, null);
        postId2 = postId;
        SightingPost foundPost = sightingPostRepository.findById(postId).get();


        String title = foundPost.getTitle();
        String content = foundPost.getContent();

        assertEquals("테스트 제목", title);
        assertEquals("테스트 내용", content);
    }

    @DisplayName("목격 정보 수정 테스트")
    @Test
    void modifySightingPost_PostIsUpdated() {
        RequestSightingPostDTO dto = new RequestSightingPostDTO();
        int postId = 7;     // 수정할 게시글 번호
        SightingPost foundPost = sightingPostRepository.findById(postId).get();

        // 수정할 내용
        dto.setTitle("제목 수정");
        dto.setContent("내용 수정");
        dto.setSightedPlace("보라매역");
        dto.setSightedAt("2025.07.12 08:30:00");
        dto.setAnimalType(AnimalType.DOG);
        dto.setBreed("말티즈");
        dto.setColor("하얀색");
        dto.setSigunguId(4);
        dto.setUserId(3);


        service.modifySightingPost(postId, dto, null);

        SightingPost updatedPost = sightingPostRepository.findById(postId).get();

        assertNotEquals(foundPost.getTitle(), updatedPost.getTitle());
        assertNotEquals(foundPost.getContent(), updatedPost.getContent());
        assertNotEquals(foundPost.getSightedPlace(), updatedPost.getSightedPlace());
        assertEquals("제목 수정", updatedPost.getTitle());
        assertEquals("내용 수정", updatedPost.getContent());



    }
}