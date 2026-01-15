package com.gemini.springai_practice.dto;

import java.time.LocalDateTime;

public class ChatMessage {
    private final String message;
    private final LocalDateTime timestamp;
    private final String sender;


    public ChatMessage(String message, LocalDateTime timestamp, String sender) {
        this.message = message;
        this.timestamp = timestamp;
        this.sender = sender;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return sender;
    }
}
