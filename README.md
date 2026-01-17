# Spring AI Gemini Practice

A beginner-friendly Spring Boot project for learning Spring AI concepts by building a simple chatbot powered by Google Gemini AI.

## What This Project Does

This is a console-based chatbot application that lets you have conversations with Google's Gemini AI directly from the command line. The application maintains your chat history and shows response times for each AI reply.

## Learning Goals

This project helps you learn:

- How to integrate AI models into Spring Boot applications
- How to use Spring AI framework with Google Gemini
- Working with ChatClient for AI conversations
- Building clean service layer architecture
- Handling chat history in memory
- Using dependency injection in Spring Boot
- Working with abstract classes and inheritance in Java

## Technologies Used

- **Java 21**: The programming language
- **Spring Boot 3.5.9**: Framework for building the application
- **Spring AI 1.1.2**: Framework for working with AI models
- **Google Gemini**: The AI model that powers the chatbot
- **Maven**: Build and dependency management tool

## Project Structure

```
src/main/java/com/gemini/springai_practice/
├── ChatApplication.java          # Main application and command line interface
├── service/
│   ├── ChatService.java         # Manages chat history and coordinates messages
│   └── GeminiService.java       # Handles communication with Gemini AI
└── dto/
    ├── ChatMessage.java         # Base class for all messages
    ├── AiMessage.java          # Represents AI responses with timing info
    ├── UserMessage.java        # Represents user input
    └── Sender.java             # Enum for message sender (USER or AI)
```

## How It Works

### Main Application (ChatApplication.java)

This is where the program starts. It creates a simple command line interface where you can:

- Type questions to chat with the AI
- Type "history" to see all previous messages
- Type "clear" to delete the chat history
- Type "exit" to quit the application

### Chat Service (ChatService.java)

This service acts as the coordinator. It:

- Stores all messages in a list (your chat history)
- Validates user input before sending to AI
- Measures how long the AI takes to respond
- Counts the number of words in AI responses
- Provides methods to view or clear history

### Gemini Service (GeminiService.java)

This service talks directly to Google's Gemini AI. It:

- Uses Spring AI's ChatClient to send messages
- Has retry logic that tries up to 5 times if the request fails
- Waits 1 second between retries
- Returns the AI's response as text

### Message Classes (dto package)

The data transfer objects organize message data:

- **ChatMessage**: Parent class with basic properties (message text, timestamp, sender)
- **UserMessage**: Extends ChatMessage for user input
- **AiMessage**: Extends ChatMessage for AI responses, adds response time tracking
- **Sender**: Enum that marks messages as either USER or AI

## Key Spring AI Concepts

### ChatClient

The ChatClient is the main way to interact with AI models in Spring AI. You use it like this:

```java
chatClient.prompt()
    .user(prompt)      // Your question
    .call()            // Send to AI
    .content()         // Get response
```

### Auto-Configuration

Spring AI automatically configures the connection to Gemini using your API key. The `@SpringBootApplication` annotation excludes embedding configurations since this project only uses the chat feature, not embeddings.

### Builder Pattern

The GeminiService uses `ChatClient.Builder` to create a configured ChatClient instance. This is a common pattern in Spring AI for customizing AI model settings.

## Setup Instructions

### Prerequisites

- Java 21 or higher installed
- Maven installed
- Google AI API key (get it from https://makersuite.google.com/app/apikey)

### Configuration

1. Create or edit the `application.yml` file in `src/main/resources/`
2. Add your Google AI API key:

```yaml
spring:
  ai:
    google:
      genai:
        api-key: YOUR_API_KEY_HERE
```

Replace `YOUR_API_KEY_HERE` with your actual API key from Google.

### Running the Application

1. Open a terminal in the project directory
2. Run the Maven command:

```bash
mvn spring-boot:run
```

3. The application will start and show:
```
Hey, Let's Chat!
Type your question, 'history', or 'exit'
You:
```

4. Start typing your questions and press Enter

<img width="681" height="235" alt="image" src="https://github.com/user-attachments/assets/ede62e25-830c-4d95-bf94-eee3ee7c8472" />
<img width="561" height="146" alt="image" src="https://github.com/user-attachments/assets/44f350b7-696b-49c9-86aa-7845dd626076" />

## Usage Examples

**Ask a question:**
```
You: What is Java?
AI: Java is a high-level, class-based, object-oriented programming language...
ResponseTime: 1.234 sec
```

**View chat history:**
```
You: history
USER: What is Java?
AI (1.234 sec): Java is a high-level...
```

**Clear history:**
```
You: clear
Conversation history cleared!
```

**Exit the application:**
```
You: exit
Goodbye
```

## Features

- **Real-time AI Conversations**: Get instant responses from Google Gemini
- **Chat History**: All messages are saved during your session
- **Response Timing**: See how long each AI response took
- **Word Count**: Each response shows the number of words
- **Error Handling**: Automatic retries if AI requests fail
- **Input Validation**: Empty messages are rejected with helpful errors

## Error Handling

The application handles common errors:

- **Empty Messages**: Shows an error if you send blank input
- **API Failures**: Automatically retries failed requests up to 5 times
- **Connection Issues**: Waits between retries to handle temporary network problems

## Learning Notes

This project demonstrates several important concepts:

1. **Dependency Injection**: Services are injected through constructors
2. **Inheritance**: UserMessage and AiMessage extend ChatMessage
3. **Polymorphism**: The `instanceof` pattern checks message types at runtime
4. **Exception Handling**: Try-catch blocks manage errors gracefully
5. **Interface Implementation**: CommandLineRunner runs code on startup
6. **Service Layer Pattern**: Business logic separated into service classes

## Limitations

- Chat history is stored in memory and lost when you restart the application
- No user authentication or multi-user support
- No persistent storage (database)
- Console-only interface (no web UI)
- Limited to text conversations (no images or files)

## Future Improvements

Ideas to extend this project:

- Add a web interface using Spring MVC
- Store chat history in a database
- Support multiple conversation threads
- Add user authentication
- Implement streaming responses
- Add support for system prompts to customize AI behavior

## Dependencies

Key dependencies from `pom.xml`:

- `spring-boot-starter-web`: Web and REST support
- `spring-ai-starter-model-google-genai`: Google Gemini integration
- `spring-ai-bom`: Spring AI framework
