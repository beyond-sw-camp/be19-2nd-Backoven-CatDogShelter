package com.backoven.catdogshelter.domain.sighting.command.domain.service;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostDTO;
import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.enumeration.AnimalType;
import org.springframework.stereotype.Service;

@Service
public class DSightingServiceImpl implements DSightingService {

    // 게시글이 범위를 벗어나는 것이 있는지 유효성 검사
    // 현재는 그냥 예외를 던지기만
    // 나중에 UX를 위해 사용자가 입력했던 값을 다시 반납해줌으로써 저장을 누르기 전단계부터 다시 작성할 수 있도록 기능 추가 필요
    //      1. Message 클래스를 만들어 성공 실패와 같이 DTO 주기.(예외 던지기X)
    //      2. 예외 클래스를 만들고 거기에 DTO를 변수로 추가하기
    @Override
    public void validatePost(RequestSightingPostDTO PostDTO) {

        if(PostDTO.getTitle() == null || PostDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("제목을 채워주세요");
        }

        if(PostDTO.getContent() == null || PostDTO.getContent().isEmpty()) {
            throw new IllegalArgumentException("제목을 채워주세요");
        }

        if(PostDTO.getSightedAt() == null || PostDTO.getSightedAt().isEmpty()) {
            throw new IllegalArgumentException("언제 보셨나요?");
        }

        if(PostDTO.getSightedPlace() == null || PostDTO.getSightedPlace().isEmpty()) {
            throw new IllegalArgumentException("어디서 보셨나요?");
        }
        if(PostDTO.getColor() == null || PostDTO.getColor().isEmpty()) {
            throw new IllegalArgumentException("어떤 색상이었나요?");
        }
        if(PostDTO.getAnimalType() == null) {
            throw new IllegalArgumentException("고양이인가요? 강아지인가요?");
        }
        if(PostDTO.getBreed() == null || PostDTO.getBreed().isEmpty()) {
            throw new IllegalArgumentException("동물의 품종은 무엇인가요?");
        }
        if(PostDTO.getSigunguId() == null) {
            throw new IllegalArgumentException("시군구를 선택해주세요");
        }
        if(PostDTO.getUserId() == null && PostDTO.getHeadId() == null) {
            throw new IllegalArgumentException("누가 작성했나요?");
        }
    }

}
