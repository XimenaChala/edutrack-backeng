package com.edutrack.communication.application;

import com.edutrack.communication.application.service.SendMessageService;
import com.edutrack.communication.domain.model.Message;
import com.edutrack.communication.domain.port.out.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SendMessageServiceTest {

    private MessageRepository repository;
    private SendMessageService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(MessageRepository.class);
        service = new SendMessageService(repository);
    }

    @Test
    void shouldSaveMessageWhenValid() {
        UUID sender = UUID.randomUUID();
        UUID receiver = UUID.randomUUID();
        String text = "Consulta academica";

        when(repository.save(any(Message.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Message result = service.sendMessage(sender, receiver, null, text);

        assertNotNull(result);
        assertEquals(text, result.getContent());
        verify(repository, times(1)).save(any(Message.class));
    }
}