package com.backoven.catdogshelter.domain.volunteer.command.application.service;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.ApplicationDTO;
import com.backoven.catdogshelter.domain.volunteer.command.application.dto.ReqApplicationDTO;
import com.backoven.catdogshelter.domain.volunteer.command.domain.aggregate.entity.ApplicationDetailsEntity;
import com.backoven.catdogshelter.domain.volunteer.command.domain.aggregate.entity.AssociationEntity;
import com.backoven.catdogshelter.domain.volunteer.command.domain.repository.ApplicationDetailsRepository;
import com.backoven.catdogshelter.domain.volunteer.command.domain.repository.AssociationRepository;

import com.backoven.catdogshelter.domain.volunteer.command.domain.repository.VolunteerUserRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ApplicationDetailsCommandServiceImpl implements ApplicationDetailsCommandService {

    private final ApplicationDetailsRepository applicationDetailsRepository;
    private final ModelMapper modelMapper;
    private final AssociationRepository associationRepository;
    private final VolunteerUserRepository volunteerUserRepository;

    @Autowired
    public ApplicationDetailsCommandServiceImpl(ApplicationDetailsRepository applicationDetailsRepository,
                                                ModelMapper modelMapper, AssociationRepository associationRepository, VolunteerUserRepository volunteerUserRepository){
        this.applicationDetailsRepository = applicationDetailsRepository;
        this.modelMapper = modelMapper;
        this.associationRepository = associationRepository;
        this.volunteerUserRepository = volunteerUserRepository;

    }

    // 봉사모임 신청내역 추가
    @Override
    public Long addApplicationDetail(Long volunteerId, ReqApplicationDTO requestDTO) {

        // 요청 데이터의 오염 방지를 위한 DTO 옮겨담기
        ApplicationDTO newApplication = modelMapper.map(requestDTO, ApplicationDTO.class);

        // 요청이 온 봉사모임의 게시글 번호(PathVariable로 넘어오는지는 의논)
        newApplication.setVolunteerId(volunteerId);

        // 요청이 온 봉사모임의 게시글에서 적어놓은 봉사시간을 가져옴
        Integer time = associationRepository.getReferenceById(volunteerId).getTime();
        newApplication.setTime(time);

        // 요청이 없으면 기본값 세팅
        if (newApplication.getStatus() == null) {newApplication.setStatus(false);}

        // 새로운 신청내역 객체 생성 후 값 넣어주기
        ApplicationDetailsEntity application = new ApplicationDetailsEntity();
        AssociationEntity association = associationRepository.getReferenceById(newApplication.getVolunteerId());

        UserEntity user = volunteerUserRepository.getReferenceById(newApplication.getUserId());

        application.setAssociation(association);
        application.setUser(user);

        application.setStatus(newApplication.getStatus());
        application.setTime(newApplication.getTime());


        // 생성된 신청의 번호를 반환하기
        Long newAppId = applicationDetailsRepository.save(application).getId();
        return newAppId;
    }

    // 봉사모임 신청 승인
    @Override
    @Transactional
    public void updateApplicationDetail(Long applicationId, ReqApplicationDTO requestDTO) {
        applicationDetailsRepository.findById(applicationId).get().setStatus(requestDTO.getStatus());
    }
}

