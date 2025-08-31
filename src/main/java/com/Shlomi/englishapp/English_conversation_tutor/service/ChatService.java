package com.Shlomi.englishapp.English_conversation_tutor.service;
import org.springframework.stereotype.Service;

import com.Shlomi.englishapp.English_conversation_tutor.domain.AiWrapper;
import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
// import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;


@Service
public class ChatService implements ChatServiceInterface{
    private final AiWrapper aiWrapper; 

    public ChatService(AiWrapper aiWrapper){
        this.aiWrapper = aiWrapper;
    }
    
    @Override
    public MessageInterface processMessage(MessageInterface request) {
        return this.aiWrapper.getResponse(request);
    }
}



