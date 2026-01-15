package com.gemini.springai_practice.dto;

import java.time.LocalDateTime;

public class ChatMessage {
    private final String message;
    private final LocalDateTime timestamp;
    private final String sender;
    private final double responseTime;


    public ChatMessage(String message, LocalDateTime timestamp, String sender, double responseTime) {
        this.message = message;
        this.timestamp = timestamp;
        this.sender = sender;
        this.responseTime = responseTime;
    }


    @Override
    public String toString() {
        return "ChatMessage{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", sender='" + sender + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return sender;
    }
}
