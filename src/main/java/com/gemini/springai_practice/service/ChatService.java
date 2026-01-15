package com.gemini.springai_practice.service;

import com.gemini.springai_practice.dto.ChatMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ChatService {

    private final GeminiService geminiService;

    public ChatService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public ChatMessage sendMessage(String message) {

        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        String trimmedMessage = message.trim();
        if (trimmedMessage.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }

        int wordLimit = 100;
        int wordCountInput = trimmedMessage.split("\\s+").length;
        if (wordCountInput > wordLimit) {
            throw new IllegalArgumentException("Message too long (max " + wordLimit + " words)");
        }

        try {
            long startTime = System.currentTimeMillis();

            String response = geminiService.askAI(trimmedMessage);

            long endTime = System.currentTimeMillis();
            long durationMs = endTime - startTime;
            double durationSeconds = durationMs / 1000.0;

            String safeResponse = (response == null) ? "" : response.trim();

            long wordCountResponse = safeResponse.isEmpty()
                    ? 0
                    : safeResponse.split("\\s+").length;

            String messageReceived = safeResponse + "\n" + wordCountResponse + " words";

            return new ChatMessage(messageReceived, LocalDateTime.now(), "AI", durationSeconds);

        } catch (Exception e) {
            throw new RuntimeException("Failed to get AI response", e);
        }
    }
}
