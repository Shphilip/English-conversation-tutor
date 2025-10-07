package com.Shlomi.englishapp.English_conversation_tutor.domain;
import org.springframework.stereotype.Component;
import com.Shlomi.englishapp.English_conversation_tutor.entities.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class AiWrapper implements WrapperInterface {
    private final AiModelInterface aiModel;
    
    public AiWrapper(AiModelInterface aiModel){
        this.aiModel = aiModel;
    }
    
    @Override
    // Get response from AI model, return MessageInterface that holds the model response
    public MessageInterface getResponse(MessageInterface userMessage) {
        JsonObject aiModelRequest = buildModelRequest(userMessage); //Create JSON object by Gemini template
        JsonObject aiModelResponse = this.aiModel.sendToModel(aiModelRequest); //Send request to AI model and get response
        MessageInterface response = convertResponseToMessage(aiModelResponse); //Parser the model response to MessageInterface
        return response;
    }

        // Create JSON request for Gemini API
        // Add user message content and the promptf 
        private JsonObject buildModelRequest(MessageInterface userMessage) {
        JsonObject requestBody = new JsonObject();
        JsonArray contents = new JsonArray();
        JsonObject content = new JsonObject();
        JsonArray parts = new JsonArray();
        JsonObject part = new JsonObject();

        // String prompt = "You are an English teacher. Your role is to have a natural, flowing conversation with the user. " +
        //         "Keep the conversation going and show interest in what the user says. " +
        //         "If the user finishes a topic, start a new topic. " +
        //         "If the user responds to your answer, ask a follow-up question or show interest in their response. " +
        //         "If the user asks a question, answer it. " +
        //         "The goal is to help the user practice English in simple and fluent language, keeping them engaged in the conversation at all times. " +
        //         "User message: " + userMessage.getMessage();

        String prompt = "You are an English teacher. Your role is to have a natural, flowing conversation with the user.\n" +
                    "Keep the conversation going and show interest in what the user says.\n" +
                    "Here is the user's previous conversation history:\n" +
                    userMessage.getConversationHistory() + "\n" + 
                    "User's new message: " + userMessage.getMessage();

        part.addProperty("text", prompt);
        parts.add(part);
        content.add("parts", parts);
        contents.add(content);
        requestBody.add("contents", contents);

        return requestBody;
    }


    //Convert the AI model response to MessageInterface type
    private MessageInterface convertResponseToMessage(JsonObject aiNodelResponse) {
        String text = aiNodelResponse
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
