# English Conversation Tutor

## Overview
A web-based English conversation practice application that enables continuous, natural conversations with an AI-powered English tutor. The application features speech recognition, text-to-speech capabilities, and session management for personalized learning experiences.

## Goals & Features
- **Continuous Conversation**: Natural dialogue flow without requiring manual "continue" buttons  
- **Speech-to-Text Integration**: Real-time voice recognition using Web Speech API  
- **Text-to-Speech Responses**: AI responses are spoken back to the user for immersive practice  
- **AI-Guided Learning**: AI model configured as an English teacher that maintains engaging conversations  
- **Session Management**: User authentication with conversation history tracking and summarization  
- **Conversation History**: Full conversation storage with summarization capabilities  

## Architecture Overview

### Technology Stack
- **Backend**: Spring Boot 3.5.5 with Java 17  
- **Database**: H2 in-memory database with JPA/Hibernate  
- **AI Integration**: Google Gemini 1.5 Flash model via REST API  
- **Frontend**: Vanilla HTML, CSS, JavaScript with Web Speech API  
- **Build Tool**: Maven  

---

## Backend Architecture

### Controller Layer
- **Functionality**: Receives user messages via JSON, delegates to service layer  
- **Request Format**: `UserMessageRequest` (username, password, message)  
- **Response Format**: `MessageInterface` (AI response message)  

### Service Layer
- **Functionality**:
  - User authentication via username/password  
  - Conversation history management (full history + summary)  
  - Session termination with "End session" command  
  - Automatic conversation summarization  

- **Data Flow**:
  1. Authenticates user credentials  
  2. Appends user message to full conversation history  
  3. Sends message to AI wrapper with conversation context  
  4. Stores AI response in conversation history  
  5. Persists updated user data  

### Domain Layer
- **AiWrapper.java**  
  - Purpose: Abstracts Gemini API communication  
  - Functionality:
    - Builds JSON requests according to Gemini API format  
    - Includes conversation history context in prompts  
    - Parses AI responses and converts to internal message format  
  - AI Prompt Template: Configures the AI model as an English teacher with natural conversation guidelines  

- **AiModel.java**  
  - Purpose: HTTP client for Gemini API  
  - Functionality:
    - Handles direct communication with Google's Generative AI API  
    - Manages API authentication and request/response processing  

---

## Frontend Architecture

### User Interface (`index.html`)
- **Login Modal**: Username/password authentication  
- **Main Interface**:
  - Dual text displays (user input, AI response)  
  - Microphone button for voice input  
  - Session instructions ("End session" command)  

### Speech Recognition (`app.js`)
- **Web Speech API Integration**:
  - Continuous speech recognition with interim results  
  - 3-second silence detection for message completion  
  - Automatic restart after AI responses  
- **Text-to-Speech**:
  - AI responses spoken using SpeechSynthesis API  

### API Communication (`script.js`)
- **Session Management**: Stores credentials in sessionStorage  
- **AJAX Requests**: POST requests to `/api/conversation` endpoint  
- **UI Updates**: Real-time text display updates for both user and AI messages  

---

## User & Message Models

### User Model
**User (implements UserInterface)**:
- **Identifier**: Long id (auto-generated primary key)  
- **Username**: String username (user login identifier)  
- **Password**: String password (user authentication)  
- **Summary of previous conversation history**: String HistoryConversationSummary (stored as TEXT in database)  
- **Current conversation database**: String fullConversationHistory (stored as LONGTEXT, contains current session messages)  

### Message Model
**UserMessage (implements MessageInterface)**:
- **Current message received**: String message (user's input text)  
- **Conversation context**: String conversationHistory (previous conversation summary)  

**AIMessage (implements MessageInterface)**:
- **AI response message**: String message (AI's response text)  
- **Conversation context**: String conversationHistory (conversation context)  

### UserMessageRequest (implements handleRequestInterface)
- **Authentication fields**: String username, String password  
- **Message content**: String message (user's current message)  

### AI Model
**AiWrapper (implements WrapperInterface)**:
- **HTTP client**: Sends requests to Gemini AI API  
- **Request processing**: Formats user message and conversation history for AI  
- **Response parsing**: Extracts AI response from JSON and returns as MessageInterface  

**AiModel (implements AiModelInterface)**:
- **Request builder**: Creates HTTP requests with proper headers and JSON payload  
- **API communication**: Handles the actual HTTP communication with external AI service  
- **Authentication**: Uses GEMINI_API_KEY environment variable  
