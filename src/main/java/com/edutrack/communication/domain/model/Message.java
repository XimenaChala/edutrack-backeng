package com.edutrack.communication.domain.model;

import com.edutrack.communication.domain.exception.InvalidMessageException;
import java.time.Instant;
import java.util.UUID;

public class Message {
    private final UUID id;
    private final UUID senderId;
    private final UUID receiverId;
    private final UUID subjectId;
    private final String content;
    private final Instant createdAt;

    public Message(UUID id, UUID senderId, UUID receiverId, UUID subjectId, String content, Instant createdAt) {
        if (senderId == null) {
            throw new InvalidMessageException("El remitente (senderId) no puede ser nulo.");
        }
        if (receiverId == null) {
            throw new InvalidMessageException("El destinatario (receiverId) no puede ser nulo.");
        }
        if (senderId.equals(receiverId)) {
            throw new InvalidMessageException("Un usuario no puede enviarse mensajes a si mismo.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new InvalidMessageException("El contenido del mensaje no puede estar vacio.");
        }
        if (content.length() > 2000) {
            throw new InvalidMessageException("El mensaje excede el limite maximo de 2000 caracteres.");
        }
        this.id = (id != null) ? id : UUID.randomUUID();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subjectId = subjectId;
        this.content = content.trim();
        this.createdAt = (createdAt != null) ? createdAt : Instant.now();
    }

    public UUID getId() { return id; }
    public UUID getSenderId() { return senderId; }
    public UUID getReceiverId() { return receiverId; }
    public UUID getSubjectId() { return subjectId; }
    public String getContent() { return content; }
    public Instant getCreatedAt() { return createdAt; }
}