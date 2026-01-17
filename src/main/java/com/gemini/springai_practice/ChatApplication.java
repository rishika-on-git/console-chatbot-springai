package com.gemini.springai_practice;

import com.gemini.springai_practice.dto.AiMessage;
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
        System.out.println("Type your question, 'history', or 'exit'");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye");
                break;
            }

            if (input.equalsIgnoreCase("history")) {
                chatService.printHistory();
                continue;
            }

            if (input.equalsIgnoreCase("clear")) {
                chatService.clearHistory();
                continue;
            }

            ChatMessage response = chatService.sendMessage(input);

            System.out.println("AI: " + response.getMessage());

            if (response instanceof AiMessage ai) {
                System.out.println("ResponseTime: " + ai.getResponseTime() + " sec");
            }

            System.out.println();
        }

        scanner.close();
    }
}
