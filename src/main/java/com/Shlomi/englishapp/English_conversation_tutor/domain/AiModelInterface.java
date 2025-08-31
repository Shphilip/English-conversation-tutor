package com.Shlomi.englishapp.English_conversation_tutor.domain;
import com.google.gson.JsonObject;

public interface AiModelInterface {
    JsonObject sendToModel(JsonObject message);
}
