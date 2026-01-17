package com.gemini.springai_practice.dto;

import java.time.LocalDateTime;

public class UserMessage extends ChatMessage {

    public UserMessage(String message, LocalDateTime timestamp) {
        super(message, timestamp, Sender.USER);
    }
}
