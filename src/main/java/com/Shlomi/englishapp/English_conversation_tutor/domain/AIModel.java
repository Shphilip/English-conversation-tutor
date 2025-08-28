package com.Shlomi.englishapp.English_conversation_tutor.domain;

import org.springframework.stereotype.Component;

import com.Shlomi.englishapp.English_conversation_tutor.entities.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;

@Component
public class AIModel implements ChatModelInterface{

    @Override
    public AIMessage getResponse(UserMessage userMessage){
        return new AIMessage();
    }

}
