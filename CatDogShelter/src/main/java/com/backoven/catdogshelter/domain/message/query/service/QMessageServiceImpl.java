package com.backoven.catdogshelter.domain.message.query.service;

import com.backoven.catdogshelter.domain.message.query.dto.MessageDetailsDTO;
import com.backoven.catdogshelter.domain.message.query.dto.MessageSummaryDTO;
import com.backoven.catdogshelter.domain.message.query.dto.RequestMessageSummaryDTO;
import com.backoven.catdogshelter.domain.message.query.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QMessageServiceImpl implements QMessageService {

    private final MessageMapper messageMapper;

    @Autowired
    public QMessageServiceImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public List<MessageSummaryDTO> findMessageSummarySubject(RequestMessageSummaryDTO subjectUser) {
        Map<String, Integer> input = new HashMap<>();
        input.put("userId", subjectUser.getUserId());
        input.put("headId", subjectUser.getHeadId());

        return messageMapper.selectMessageSummarySubject(input);
    }

    @Override
    public List<MessageSummaryDTO> findMessageSummarySend(RequestMessageSummaryDTO sendUser) {
        Map<String, Integer> input = new HashMap<>();
        input.put("userId", sendUser.getUserId());
        input.put("headId", sendUser.getHeadId());

        return messageMapper.selectMessageSummarySend(input);
    }

    @Override
    @Transactional
    public MessageDetailsDTO findMessageDetails(int messageId) {

        MessageDetailsDTO messageDetailsDTO = messageMapper.selectMessageDetails(messageId);

        messageMapper.updateMessageStatus(messageId);

        return messageDetailsDTO;
    }
}
