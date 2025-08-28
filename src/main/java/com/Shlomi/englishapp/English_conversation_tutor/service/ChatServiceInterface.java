package com.Shlomi.englishapp.English_conversation_tutor.service;

import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;

public interface ChatServiceInterface {
    MessageInterface processMessage(MessageInterface message);
}
