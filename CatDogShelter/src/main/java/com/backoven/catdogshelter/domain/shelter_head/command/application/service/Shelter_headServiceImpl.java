package com.backoven.catdogshelter.domain.shelter_head.command.application.service;

import com.backoven.catdogshelter.domain.shelter_head.command.application.dto.Shelter_headDTO;
import com.backoven.catdogshelter.domain.shelter_head.command.domain.aggregate.entity.Shelter_headEntity;
import com.backoven.catdogshelter.domain.shelter_head.command.domain.repository.Shelter_headRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class Shelter_headServiceImpl implements Shelter_headService {

    Shelter_headRepository shelter_headRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public Shelter_headServiceImpl(Shelter_headRepository shelter_headRepository,
                                   ModelMapper modelMapper) {
        this.shelter_headRepository = shelter_headRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registUser(Shelter_headDTO shelter_head) {

        shelter_head.setHeadAccount(UUID.randomUUID().toString());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Shelter_headEntity shelterHeadEntity =
                modelMapper.map(shelter_head, Shelter_headEntity.class);

        shelter_headRepository.save(shelterHeadEntity);
    }
}
