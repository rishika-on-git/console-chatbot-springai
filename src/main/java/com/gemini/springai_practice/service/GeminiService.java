package com.gemini.springai_practice.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {
    private final ChatClient chatClient;
    final int MAX_RETRIES = 5;

    public GeminiService(ChatClient.Builder geminiChatClientBuilder) {
        this.chatClient = geminiChatClientBuilder.build();
    }

    public String askAI(String prompt) {
        int attempts = 0;
        Exception lastException = null;

        while (attempts < MAX_RETRIES) {
            try {
                return chatClient.prompt()
                        .user(prompt)
                        .call()
                        .content();
            } catch (Exception e) {
                lastException = e;
                attempts++;
                if (attempts < MAX_RETRIES) {
                    System.out.println("Request failed, retrying... (" + attempts + "/" + MAX_RETRIES + ")");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Retry interrupted", ie);
                    }
                }
            }
        }

        throw new RuntimeException("Failed after " + MAX_RETRIES + " attempts", lastException);
    }

}
