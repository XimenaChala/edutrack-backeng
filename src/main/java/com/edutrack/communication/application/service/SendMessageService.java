package com.edutrack.communication.application.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edutrack.communication.domain.model.Message;
import com.edutrack.communication.domain.port.in.SendMessageUseCase;
import com.edutrack.communication.domain.port.out.MessageRepository;

@Service
public class SendMessageService implements SendMessageUseCase {

    private final MessageRepository messageRepository;

    public SendMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message sendMessage(UUID senderId, UUID receiverId, UUID subjectId, String content) {
        Message message = new Message(
            UUID.randomUUID(),
            senderId,
            receiverId,
            subjectId,
            content,
            Instant.now()
        );
        return messageRepository.save(message);
    }
}