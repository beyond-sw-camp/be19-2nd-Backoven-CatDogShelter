package com.backoven.catdogshelter.domain.message.query.service;

import com.backoven.catdogshelter.domain.message.query.dto.MessageDetailsDTO;
import com.backoven.catdogshelter.domain.message.query.dto.MessageSummaryDTO;
import com.backoven.catdogshelter.domain.message.query.dto.RequestMessageSummaryDTO;

import java.util.List;

public interface
QMessageService {
    List<MessageSummaryDTO> findMessageSummarySubject(RequestMessageSummaryDTO subjectUser);

    List<MessageSummaryDTO> findMessageSummarySend(RequestMessageSummaryDTO sendUser);

    MessageDetailsDTO findMessageDetails(int messageId);
}
