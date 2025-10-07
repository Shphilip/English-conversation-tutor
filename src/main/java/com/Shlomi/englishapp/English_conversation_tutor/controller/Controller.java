package com.Shlomi.englishapp.English_conversation_tutor.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessageRequest;
import com.Shlomi.englishapp.English_conversation_tutor.service.ChatServiceInterface;

@RestController
@RequestMapping("/api/conversation")

public class Controller implements ControllerInterface{
    private final ChatServiceInterface chatService;

    public Controller(ChatServiceInterface chatService){
        this.chatService = chatService;
    }

    @PostMapping
    @Override
    public MessageInterface handleRequest(@RequestBody UserMessageRequest request){
        
        MessageInterface response = chatService.processMessage(request);
        return response;
    }


}
