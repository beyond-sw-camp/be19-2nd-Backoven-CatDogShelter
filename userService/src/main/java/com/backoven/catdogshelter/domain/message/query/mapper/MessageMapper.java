package com.backoven.catdogshelter.domain.message.query.mapper;

import com.backoven.catdogshelter.domain.message.query.dto.MessageDetailsDTO;
import com.backoven.catdogshelter.domain.message.query.dto.MessageSummaryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {
    List<MessageSummaryDTO> selectMessageSummarySubject(Map<String, Integer> input);

    List<MessageSummaryDTO> selectMessageSummarySend(Map<String, Integer> input);

    MessageDetailsDTO selectMessageDetails(int messageId);

    void updateMessageStatus(int messageId);
}
