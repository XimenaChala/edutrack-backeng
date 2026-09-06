package com.edutrack.communication.infrastructure.persistence;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class MessageJpaEntity {

    @Id
    private UUID id;

    @Column(name = "sender_id", nullable = false)
    private UUID senderId;

    @Column(name = "receiver_id", nullable = false)
    private UUID receiverId;

    @Column(name = "subject_id")
    private UUID subjectId;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public MessageJpaEntity() {}

    public MessageJpaEntity(UUID id, UUID senderId, UUID receiverId, UUID subjectId, String content, Instant createdAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subjectId = subjectId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public UUID getSenderId() { return senderId; }
    public UUID getReceiverId() { return receiverId; }
    public UUID getSubjectId() { return subjectId; }
    public String getContent() { return content; }
    public Instant getCreatedAt() { return createdAt; }
}