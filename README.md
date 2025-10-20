# English Conversation Tutor

A web-based English conversation practice application that enables natural, continuous dialogues with an AI-powered English tutor.
This project integrates speech recognition, text-to-speech, and AI-guided learning into a seamless, personalized web experience.

---

## Overview

The system allows users to engage in continuous English conversations with an AI tutor that listens, understands, and responds naturally.
It provides both speech-to-text and text-to-speech integration for a fully interactive learning experience.

---

## Key Features

* **AI-Guided Conversations** – Speak naturally with an AI English tutor powered by Google Gemini
* **Speech-to-Text** – Real-time voice recognition via the Web Speech API
* **Text-to-Speech** – AI replies are spoken aloud for immersive interaction
* **Continuous Flow** – Natural conversation without clicking “continue”
* **User Sessions** – Login system with personalized conversation history
* **Conversation Summaries** – Automatic summarization of past sessions

---

## Technology Stack

| Layer      | Technology                            |
| ---------- | ------------------------------------- |
| Frontend   | HTML, CSS, JavaScript, Web Speech API |
| Backend    | Java 17, Spring Boot 3.5.5            |
| Database   | H2 (in-memory, JPA/Hibernate)         |
| AI Model   | Google Gemini 1.5 Flash (REST API)    |
| Build Tool | Maven                                 |

---

## Architecture Overview

The system follows a layered architecture:

* **Controller Layer** – Handles REST API requests and responses
* **Service Layer** – Manages authentication, conversation logic, and history
* **Domain Layer** – Wraps AI communication with Google Gemini API
* **Frontend** – Handles speech input/output and user interaction through JavaScript

For full class-level details and system behavior, see the accompanying **Specification Document** in the repository.

---

## Getting Started

### Prerequisites

* Java 17 or higher
* Maven 3.6 or higher
* Google Gemini API key

### Installation

```bash
git clone https://github.com/Shphilip/English-conversation-tutor.git
cd English-conversation-tutor
export GEMINI_API_KEY=your_api_key_here
./mvnw spring-boot:run
```

Then open a browser and go to `http://localhost:8080`.

---

## Usage

1. Log in using your username and password.
2. Click the microphone icon to start speaking.
3. Converse naturally — the AI will listen and respond.
4. Say “End session” to finish and save your session history.

---

## API Endpoint

**POST** `/api/conversation`

**Request body:**

```json
{
  "username": "string",
  "password": "string",
  "message": "string"
}
```

**Response:**

```json
{
  "message": "string",
  "conversationHistory": "string"
}
```

---

## Configuration

**Environment Variable**

```bash
GEMINI_API_KEY=your_google_gemini_api_key
```

**Application Properties**
Found at `src/main/resources/application.properties`.

---

## Project Structure

```
English-conversation-tutor/
├── src/
│   ├── main/
│   │   ├── java/com/Shlomi/englishapp/English_conversation_tutor/
│   │   │   ├── controller/
│   │   │   ├── domain/
│   │   │   ├── entities/
│   │   │   └── service/
│   │   └── resources/static/
│   │       ├── index.html
│   │       ├── app.js
│   │       ├── script.js
│   │       └── styles.css
├── pom.xml
├── data/
├── README.md
└── SPECIFICATION.md
```

---

## Future Improvements

* Add user progress tracking and analytics
* Introduce multi-language support
* Integrate a persistent cloud database
* Enhance voice emotion recognition for more natural responses

---

## Author

**Shlomi Philip**
Computer Science Student, Hebrew University of Jerusalem
Email: [shlomi.philip@mail.huji.ac.il](mailto:shlomi.philip@mail.huji.ac.il)
