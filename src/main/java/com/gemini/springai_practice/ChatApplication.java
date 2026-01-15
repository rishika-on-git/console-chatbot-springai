package com.gemini.springai_practice;

import com.gemini.springai_practice.dto.ChatMessage;
import com.gemini.springai_practice.service.ChatService;
import org.springframework.ai.model.google.genai.autoconfigure.embedding.GoogleGenAiEmbeddingConnectionAutoConfiguration;
import org.springframework.ai.model.google.genai.autoconfigure.embedding.GoogleGenAiTextEmbeddingAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication(exclude = {
        GoogleGenAiEmbeddingConnectionAutoConfiguration.class,
        GoogleGenAiTextEmbeddingAutoConfiguration.class
})

public class ChatApplication implements CommandLineRunner {

    private final ChatService chatService;

    public ChatApplication(ChatService chatService) {
        this.chatService = chatService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

    @Override
    public void run(String... args) {
        startChat();
    }

    private void startChat() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hey, Let's Chat!");
        System.out.println("**********");
        System.out.println("Type your question or 'exit' to quit");

        while (true) {
            System.out.print("You: ");
            String userQuery = scanner.nextLine().trim();

            if (userQuery.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye");
                break;
            }

            if (userQuery.isEmpty()) {
                continue;
            }

            ChatMessage response = chatService.sendMessage(userQuery);
            System.out.println("AI: " + response.getMessage());
            System.out.println();
        }

        scanner.close();
    }
}

