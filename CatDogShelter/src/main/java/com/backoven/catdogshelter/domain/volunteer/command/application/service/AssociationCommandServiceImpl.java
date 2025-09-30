package com.backoven.catdogshelter.domain.volunteer.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import com.backoven.catdogshelter.common.entity.SigunguEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.AssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.ReqAssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.command.domain.aggregate.entity.AssociationEntity;
import com.backoven.catdogshelter.domain.volunteer.command.domain.repository.AssociationRepository;
import com.backoven.catdogshelter.domain.volunteer.command.domain.repository.VolunteerShelterHeadRepository;
import com.backoven.catdogshelter.domain.volunteer.command.domain.repository.VolunteerSigunguRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AssociationCommandServiceImpl implements AssociationCommandService {
    private final AssociationRepository associationRepository;
    private final VolunteerShelterHeadRepository volunteerShelterHeadRepository;
    private final VolunteerSigunguRepository volunteerSigunguRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AssociationCommandServiceImpl(AssociationRepository associationRepository, VolunteerShelterHeadRepository volunteerShelterHeadRepository, VolunteerSigunguRepository volunteerSigunguRepository, ModelMapper modelMapper) {
        this.associationRepository = associationRepository;
        this.volunteerShelterHeadRepository = volunteerShelterHeadRepository;
        this.volunteerSigunguRepository = volunteerSigunguRepository;
        this.modelMapper = modelMapper;
    }

    // 봉사모임 게시글 작성
    @Override
    @Transactional
    public void writeAssociation(ReqAssociationDTO requestDTO) {

        // 요청 데이터의 오염을 방지하기 위한 서비스와 레포지토리 계층 간의 DTO
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AssociationDTO associationDTO = modelMapper.map(requestDTO, AssociationDTO.class);

        // createdAt은 서버시간으로 작성 나머지는 Boolean은 기본값으로 넣기
        associationDTO.setCreatedAt(DateTimeUtil.now());


        log.info("확인0: {}", associationDTO);
        // 1) 요청을 엔티티로 매핑
        AssociationEntity associationEntity = modelMapper.map(associationDTO, AssociationEntity.class);
        log.info("확인1: {}", associationEntity);

        // 2) 연관은 "프록시"로만 연결 (중요!) 나중에 시큐리티 적용을 위한 보호소장 회원 및 소속 시군쿠 코드의 그래프 검색
        ShelterHeadEntity head = volunteerShelterHeadRepository.getReferenceById(associationDTO.getHeadId()); // 타입에 맞게
        SigunguEntity sigungu = volunteerSigunguRepository.getReferenceById(associationDTO.getSigunguId());
        log.info("확인2: {}", associationEntity);
        head.setId(associationDTO.getHeadId().intValue());
        sigungu.setId(associationDTO.getSigunguId());
        log.info("확인3: {}", associationEntity);

        associationEntity.setShelterHead(head);
        associationEntity.setSigungu(sigungu);
        log.info("확인4: {}", associationEntity);


        // 3) 저장 (연관은 기존 레코드를 참조만 하므로 persist 대상 아님 → detached 오류 X)
        associationRepository.save(associationEntity);

    }

    // 봉사모임 게시글 수정
    @Override
    @Transactional
    public void modifyAssociation(ReqAssociationDTO modifyAssociation, Long associationId) {

        // 요청의 오염 방지
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AssociationDTO associationDTO = modelMapper.map(modifyAssociation, AssociationDTO.class);

        // 게시글을 찾을 수 없을 때의 예외 핸들러 필요
        AssociationEntity foundAssociation = associationRepository.getReferenceById(associationId);

        // 수정
        foundAssociation.setTitle(associationDTO.getTitle());
        foundAssociation.setContent(associationDTO.getContent());
        foundAssociation.setTime(associationDTO.getTime());
        foundAssociation.setStartDate(associationDTO.getStartDate());
        foundAssociation.setDetailAddress(associationDTO.getDetailAddress());
        foundAssociation.setNumberOfPeople(associationDTO.getNumberOfPeople());
    }


}
