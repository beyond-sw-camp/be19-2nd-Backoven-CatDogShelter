package com.backoven.catdogshelter.domain.message.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.message.command.application.dto.RequestMessageDTO;
import com.backoven.catdogshelter.domain.message.command.domain.aggregate.entity.Message;
import com.backoven.catdogshelter.domain.message.command.domain.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AMessageServiceImpl implements AMessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AMessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public int registMessage(RequestMessageDTO newMessageDTO) {

        if(newMessageDTO.getSubjectNumber() == null){
            newMessageDTO.setSubjectNumber(-1);
        }
        
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Message newMessage = modelMapper.map(newMessageDTO, Message.class);
        newMessage.setCreatedAt(DateTimeUtil.now());
        
        messageRepository.save(newMessage);
        
        // subjectNumber 가 없으면 자기 자신을 등록
        if(newMessage.getSubjectNumber() == -1){
            newMessage.setSubjectNumber(newMessage.getId());
            messageRepository.save(newMessage);
        }
        
        return newMessage.getId();
    }

    @Override
    public void removeMessage(int messageId) {
        Message message = messageRepository.findById(messageId).orElse(null);

        messageRepository.delete(message);
    }
}
