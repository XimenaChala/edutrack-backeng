package com.edutrack.communication.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.edutrack.communication.domain.model.Message;
import com.edutrack.communication.domain.port.out.MessageRepository;

@Component
public class JpaMessageRepositoryAdapter implements MessageRepository {

    private final SpringDataMessageRepository repository;

    public JpaMessageRepositoryAdapter(SpringDataMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message save(Message message) {
        MessageJpaEntity entity = new MessageJpaEntity(
            message.getId(),
            message.getSenderId(),
            message.getReceiverId(),
            message.getSubjectId(),
            message.getContent(),
            message.getCreatedAt()
        );
        MessageJpaEntity saved = repository.save(entity);
        return new Message(saved.getId(), saved.getSenderId(), saved.getReceiverId(), saved.getSubjectId(), saved.getContent(), saved.getCreatedAt());
    }

    @Override
    public List<Message> findConversation(UUID user1, UUID user2) {
        return repository.findConversationHistory(user1, user2)
            .stream()
            .map(e -> new Message(e.getId(), e.getSenderId(), e.getReceiverId(), e.getSubjectId(), e.getContent(), e.getCreatedAt()))
            .toList();
    }
}