package com.edutrack.communication.infrastructure.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edutrack.communication.domain.model.Message;
import com.edutrack.communication.domain.port.in.SendMessageUseCase;
import com.edutrack.communication.domain.port.out.MessageRepository;
import com.edutrack.communication.infrastructure.web.dto.MessageResponse;
import com.edutrack.communication.infrastructure.web.dto.SendMessageRequest;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final SendMessageUseCase sendMessageUseCase;
    private final MessageRepository messageRepository;

    public MessageController(SendMessageUseCase sendMessageUseCase, MessageRepository messageRepository) {
        this.sendMessageUseCase = sendMessageUseCase;
        this.messageRepository = messageRepository;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@Valid @RequestBody SendMessageRequest request) {
        Message sent = sendMessageUseCase.sendMessage(
            request.senderId(),
            request.receiverId(),
            request.subjectId(),
            request.content()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponse.fromDomain(sent));
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<MessageResponse>> getConversation(
            @RequestParam UUID user1,
            @RequestParam UUID user2) {
        List<MessageResponse> list = messageRepository.findConversation(user1, user2)
            .stream()
            .map(MessageResponse::fromDomain)
            .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/health")
    public ResponseEntity<java.util.Map<String, String>> healthCheck() {
        return ResponseEntity.ok(java.util.Map.of(
            "status", "UP",
            "service", "communication-service",
            "timestamp", java.time.Instant.now().toString()
        ));
    }
}