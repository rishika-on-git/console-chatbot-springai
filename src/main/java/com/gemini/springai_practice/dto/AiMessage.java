package com.gemini.springai_practice.dto;

import java.time.LocalDateTime;

public class AiMessage extends ChatMessage {

    private final double responseTime;

    public AiMessage(String message, double responseTime, LocalDateTime timestamp) {
        super(message, timestamp, Sender.AI);
        this.responseTime = responseTime;
    }

    public double getResponseTime() {
        return responseTime;
    }
}
