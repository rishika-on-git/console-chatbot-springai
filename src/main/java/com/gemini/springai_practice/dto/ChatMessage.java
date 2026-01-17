package com.gemini.springai_practice.dto;

import java.time.LocalDateTime;

public abstract class ChatMessage {

    protected String message;
    protected LocalDateTime timestamp;
    protected Sender sender;

    protected ChatMessage(String message, LocalDateTime timestamp, Sender sender) {
        this.message = message;
        this.timestamp = timestamp;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Sender getSender() {
        return sender;
    }
}
