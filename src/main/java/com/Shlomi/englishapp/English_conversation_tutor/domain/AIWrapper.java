package com.Shlomi.englishapp.English_conversation_tutor.domain;

import org.springframework.stereotype.Component;

import com.Shlomi.englishapp.English_conversation_tutor.entities.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
// import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class AIWrapper implements WrapperInterface {
    private final ChatModelInterface aiModel;
    private final Gson gson = new Gson();

    public AIWrapper(ChatModelInterface aiModel) {
        this.aiModel = aiModel;
    }

    @Override
    // Get response from AI model, return MessageInterface that holds the model response
    public MessageInterface getResponse(MessageInterface userMessage) {
        JsonObject geminiRequest = buildModelRequest(userMessage); //Create JSON object by Gemini template
        JsonObject geminiResponse = this.aiModel.sendToModel(geminiRequest); //Send request to AI model and get response
        MessageInterface response = parseGeminiResponse(geminiResponse); //Parser the model response to MessageInterface
        return response;
    }

    // Create JSON request for Gemini API
    // Add user message content and the promptf 
   private JsonObject buildModelRequest(String userMessage) {
    JsonObject requestBody = new JsonObject();

    // "contents" array
    JsonArray contents = new JsonArray();
    JsonObject content = new JsonObject();

    // "parts" array
    JsonArray parts = new JsonArray();
    JsonObject part = new JsonObject();

    String prompt = "You are an English teacher. Your role is to have a natural, flowing conversation with the user. " +
            "Keep the conversation going and show interest in what the user says. " +
            "If the user finishes a topic, start a new topic. " +
            "If the user responds to your answer, ask a follow-up question or show interest in their response. " +
            "If the user asks a question, answer it. " +
            "The goal is to help the user practice English in simple and fluent language, keeping them engaged in the conversation at all times. " +
            "User message: " + userMessage;

    // Add prompt to "parts"
    part.addProperty("text", prompt);
    parts.add(part);

    // Add parts to content
    content.add("parts", parts);
    contents.add(content);

    // Add contents to request body
    requestBody.add("contents", contents);

    return requestBody;
}


    
    private MessageInterface parseGeminiResponse(JsonObject geminiResponse) {
    // Navigate directly through JSON
    String text = geminiResponse
        .getAsJsonArray("candidates")
        .get(0).getAsJsonObject()
        .getAsJsonObject("content")
        .getAsJsonArray("parts")
        .get(0).getAsJsonObject()
        .get("text").getAsString();

    MessageInterface aiMessage = new AIMessage();
    aiMessage.setMessage(text);
    return aiMessage;
    }
}
