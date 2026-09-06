package com.edutrack.communication.domain.port.in;

import java.util.UUID;

import com.edutrack.communication.domain.model.Message;

public interface SendMessageUseCase {
    Message sendMessage(UUID senderId, UUID receiverId, UUID subjectId, String content);
}