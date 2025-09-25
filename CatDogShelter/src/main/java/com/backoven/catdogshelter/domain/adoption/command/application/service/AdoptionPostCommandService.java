package com.backoven.catdogshelter.domain.adoption.command.application.service;

import com.backoven.catdogshelter.domain.adoption.command.application.dto.AdoptionPostCommandDTO;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPostEntity;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.AdoptionPostStatus;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.AnimalType;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.Gender;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.YnOption;
import com.backoven.catdogshelter.domain.adoption.command.domain.repository.AdoptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdoptionPostCommandService {
    private final AdoptionRepository adoptionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdoptionPostCommandService(AdoptionRepository adoptionRepository
                                     ,ModelMapper modelMapper) {
        this.adoptionRepository = adoptionRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void registAdoptionPost(AdoptionPostCommandDTO newPost) {
        adoptionRepository.save(modelMapper.map(newPost, AdoptionPostEntity.class));
    }

    @Transactional
    public void modifyAdoptionPost(AdoptionPostCommandDTO modifyPost) {
        AdoptionPostEntity foundPost =
                adoptionRepository.findById(modifyPost.getId())
                        .orElseThrow(IllegalArgumentException::new);

        foundPost.setTitle(modifyPost.getTitle());
        foundPost.setContent(modifyPost.getContent());
        foundPost.setAnimalType(modifyPost.getAnimalType());
        foundPost.setAge(modifyPost.getAge());
        foundPost.setColor(modifyPost.getColor());
        foundPost.setGender(modifyPost.getGender());
        foundPost.setWeight(modifyPost.getWeight());
        foundPost.setVaccination(modifyPost.getVaccination());
        foundPost.setNeutering(modifyPost.getNeutering());
        foundPost.setStatus(modifyPost.getStatus());
    }


    @Transactional
    public void deleteAdoptionPost(int deletePostId) {
        AdoptionPostEntity foundPost =
                adoptionRepository.findById(deletePostId)
                        .orElseThrow(IllegalArgumentException::new);
        foundPost.setIsBlind(true);

    }
}
