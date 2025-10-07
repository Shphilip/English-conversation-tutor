package com.Shlomi.englishapp.English_conversation_tutor.service;

import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
import com.Shlomi.englishapp.English_conversation_tutor.entities.handleRequestInterface;

public interface ChatServiceInterface {
    MessageInterface processMessage(handleRequestInterface message);
}
