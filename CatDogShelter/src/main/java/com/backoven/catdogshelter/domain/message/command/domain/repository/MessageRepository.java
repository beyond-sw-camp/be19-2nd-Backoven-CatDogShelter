package com.backoven.catdogshelter.domain.message.command.domain.repository;

import com.backoven.catdogshelter.domain.message.command.domain.aggregate.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
