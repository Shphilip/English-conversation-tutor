package com.Shlomi.englishapp.English_conversation_tutor.service;
import org.springframework.stereotype.Service;

import com.Shlomi.englishapp.English_conversation_tutor.domain.AIWrapper;
import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;


@Service
public class ChatService {
    private final AIWrapper aiWrapper; 

    public ChatService(AIWrapper aiWrapper){
        this.aiWrapper = aiWrapper;
    }
    
    public String processMessage(UserMessage request) {
        return this.aiWrapper.getResponse(request).getMassage();
    }
}



