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
        try {
            String response = geminiService.askAI(message);
            return new ChatMessage(response, LocalDateTime.now(), "AI");
        } catch (Exception e) {
            throw new RuntimeException("Failed to get AI response", e);
        }
    }
}