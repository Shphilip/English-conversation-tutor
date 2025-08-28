package com.Shlomi.englishapp.English_conversation_tutor.controller;

import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;

public interface ControllerInterface {
    MessageInterface handleRequest(MessageInterface message);
}
