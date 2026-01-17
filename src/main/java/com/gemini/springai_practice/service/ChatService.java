package com.gemini.springai_practice.service;

import com.gemini.springai_practice.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private final GeminiService geminiService;
    private final List<ChatMessage> history = new ArrayList<>();

    public ChatService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public ChatMessage sendMessage(String input) {

        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }

        String trimmed = input.trim();

        UserMessage user = new UserMessage(trimmed, LocalDateTime.now());
        history.add(user);

        try {
            long start = System.currentTimeMillis();
            String response = geminiService.askAI(trimmed);
            long end = System.currentTimeMillis();

            double responseTimeSec = (end - start) / 1000.0;

            String safeResponse = response == null ? "" : response.trim();
            long words = safeResponse.isEmpty() ? 0 : safeResponse.split("\\s+").length;

            String finalResponse = safeResponse + "\n" + words + " words";

            AiMessage ai = new AiMessage(finalResponse, responseTimeSec, LocalDateTime.now());
            history.add(ai);

            return ai;

        } catch (Exception e) {
            throw new RuntimeException("Failed to get AI response", e);
        }
    }

    public void printHistory() {
        for (ChatMessage msg : history) {
            if (msg instanceof AiMessage ai) {
                System.out.println("AI (" + ai.getResponseTime() + " sec): " + ai.getMessage());
            } else {
                System.out.println("USER: " + msg.getMessage());
            }
        }
    }
}
