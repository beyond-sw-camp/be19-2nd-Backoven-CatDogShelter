package com.backoven.catdogshelter.domain.adoption.command.application.service.adminService;

import com.backoven.catdogshelter.domain.adoption.command.application.dto.AdoptionPostCommentReportDTO;
import com.backoven.catdogshelter.domain.adoption.command.application.dto.AdoptionPostReportDTO;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostCommentReportEntity;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostReportEntity;
import com.backoven.catdogshelter.domain.adoption.command.domain.repository.AdoptionPostCommentReportRepository;
import com.backoven.catdogshelter.domain.adoption.command.domain.repository.AdoptionPostReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdoptionPostAdmindService {
    private final AdoptionPostReportRepository adoptionPostReportRepository;
    private final AdoptionPostCommentReportRepository adoptionPostCommentReportRepository;
    private ModelMapper modelMapper;
    @Autowired
    public AdoptionPostAdmindService(AdoptionPostReportRepository adoptionPostReportRepository,
                                     AdoptionPostCommentReportRepository adoptionPostCommentReportRepository,
                                     ModelMapper modelMapper) {
        this.adoptionPostReportRepository = adoptionPostReportRepository;
        this.adoptionPostCommentReportRepository = adoptionPostCommentReportRepository;
        this.modelMapper = modelMapper;
    }

    public List<AdoptionPostReportDTO> selectAllAdoptionPostReport() {
        List<AdoptionPostReportEntity> foundPostReport =
                adoptionPostReportRepository.findAll();
        return foundPostReport.stream()
                .map(entity -> AdoptionPostReportDTO.builder()
                        .id(entity.getId())
                        .category(entity.getCategory().name())
                        .etcDetail(entity.getEtcDetail())
                        .createdAt(entity.getCreatedAt())
                        .status(entity.getStatus())
                        .adoptionPostId(entity.getAdoptionPost() != null ? entity.getAdoptionPost().getId() : null)
                        .userId(entity.getUser() != null ? entity.getUser().getUserId() : null)
                        .headId(entity.getShelterHead() != null ? entity.getShelterHead().getId() : null)
                        .build())
                .collect(Collectors.toList());
    }

    public List<AdoptionPostCommentReportDTO> selectAllAdoptionPostCommentReport() {
        List<AdoptionPostCommentReportEntity> foundPostCommentReport =
                adoptionPostCommentReportRepository.findAll();
        return foundPostCommentReport.stream()
                .map(entity -> AdoptionPostCommentReportDTO.builder()
                        .id(entity.getId())
                        .category(entity.getCategory().name())
                        .etcDetail(entity.getEtcDetail())
                        .createdAt(entity.getCreatedAt())
                        .status(entity.getStatus())
                        .commentId(entity.getComment() != null ? entity.getComment().getId() : null)
                        .userId(entity.getUser() != null ? entity.getUser().getUserId() : null)
                        .headId(entity.getShelterHead() != null ? entity.getShelterHead().getId() : null)
                        .build())
                .collect(Collectors.toList());
    }
}
