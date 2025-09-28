package com.backoven.catdogshelter.domain.message.command.application.service;

import com.backoven.catdogshelter.domain.message.command.application.dto.RequestMessageDTO;

public interface AMessageService {
    int registMessage(RequestMessageDTO newMessageDTO);

    void removeMessage(int messageId);
}
