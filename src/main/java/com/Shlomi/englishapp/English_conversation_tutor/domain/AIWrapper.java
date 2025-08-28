package com.Shlomi.englishapp.English_conversation_tutor.domain;

import com.Shlomi.englishapp.English_conversation_tutor.conversation.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.conversation.UserMessage;

public class AIWrapper{
    private final AIModel aiModel = new AIModel();

    public AIMessage getResponse(UserMessage userMessage){
        return this.aiModel.getResponse(userMessage);
    }

    
}