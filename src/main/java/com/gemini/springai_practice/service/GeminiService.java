package com.gemini.springai_practice.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {
    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder geminiChatClientBuilder) {
        this.chatClient = geminiChatClientBuilder.build();
    }

    public String askAI(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

}
