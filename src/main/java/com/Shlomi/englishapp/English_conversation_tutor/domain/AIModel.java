package com.Shlomi.englishapp.English_conversation_tutor.domain;

import org.springframework.stereotype.Component;
import com.Shlomi.englishapp.English_conversation_tutor.conversation.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.conversation.UserMessage;
import com.Shlomi.englishapp.English_conversation_tutor.domain.ChatModel;

@Component
public class AIModel implements ChatModel{

    @Override
    public AIMessage getResponse(UserMessage userMessage){
        return new AIMessage();
    }

}
