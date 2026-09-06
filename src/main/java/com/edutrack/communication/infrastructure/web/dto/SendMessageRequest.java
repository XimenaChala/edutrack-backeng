package com.edutrack.communication.infrastructure.web.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SendMessageRequest(
    @NotNull(message = "senderId es obligatorio") UUID senderId,
    @NotNull(message = "receiverId es obligatorio") UUID receiverId,
    UUID subjectId,
    @NotBlank(message = "content no puede estar vacio") String content
) {}