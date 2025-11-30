package com.Shlomi.englishapp.English_conversation_tutor.controller;
import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessageRequest;

public interface ControllerInterface {
    MessageInterface handleRequest(UserMessageRequest message);
}
