package com.Shlomi.englishapp.English_conversation_tutor.domain;
import com.Shlomi.englishapp.English_conversation_tutor.entities.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;

public interface ChatModelInterface {
    AIMessage getResponse(UserMessage message);
}
