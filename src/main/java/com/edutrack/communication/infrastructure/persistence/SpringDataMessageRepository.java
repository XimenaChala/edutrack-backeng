package com.edutrack.communication.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataMessageRepository extends JpaRepository<MessageJpaEntity, UUID> {
    @Query("SELECT m FROM MessageJpaEntity m WHERE (m.senderId = :u1 AND m.receiverId = :u2) OR (m.senderId = :u2 AND m.receiverId = :u1) ORDER BY m.createdAt ASC")
    List<MessageJpaEntity> findConversationHistory(@Param("u1") UUID user1, @Param("u2") UUID user2);
}