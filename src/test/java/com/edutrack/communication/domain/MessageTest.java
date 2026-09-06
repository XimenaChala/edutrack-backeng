package com.edutrack.communication.domain;

import com.edutrack.communication.domain.exception.InvalidMessageException;
import com.edutrack.communication.domain.model.Message;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void shouldCreateValidMessageSuccessfully() {
        UUID sender = UUID.randomUUID();
        UUID receiver = UUID.randomUUID();
        String text = "Profesor, tengo una duda sobre la tarea de matematicas.";

        Message msg = new Message(UUID.randomUUID(), sender, receiver, null, text, Instant.now());

        assertNotNull(msg.getId());
        assertEquals(text, msg.getContent());
        assertEquals(sender, msg.getSenderId());
    }

    @Test
    void shouldThrowExceptionWhenContentIsEmpty() {
        UUID sender = UUID.randomUUID();
        UUID receiver = UUID.randomUUID();

        assertThrows(InvalidMessageException.class, () ->
                new Message(UUID.randomUUID(), sender, receiver, null, "   ", Instant.now())
        );
    }

    @Test
    void shouldThrowExceptionWhenSenderEqualsReceiver() {
        UUID user = UUID.randomUUID();

        assertThrows(InvalidMessageException.class, () ->
                new Message(UUID.randomUUID(), user, user, null, "Hola a mi mismo", Instant.now())
        );
    }
}