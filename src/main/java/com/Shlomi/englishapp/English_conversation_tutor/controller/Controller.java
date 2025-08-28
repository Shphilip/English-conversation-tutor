package com.Shlomi.englishapp.English_conversation_tutor.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shlomi.englishapp.English_conversation_tutor.conversation.UserMessage;
import com.Shlomi.englishapp.English_conversation_tutor.service.ChatService;

@RestController
@RequestMapping("/api/conversation")

public class Controller {
    private final ChatService chatService;

    public Controller(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping
    public String handleRequest(@RequestBody UserMessage request) {
        return chatService.processMessage(request);
    }

}
