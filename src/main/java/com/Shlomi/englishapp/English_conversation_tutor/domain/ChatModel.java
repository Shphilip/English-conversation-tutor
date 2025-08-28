package com.Shlomi.englishapp.English_conversation_tutor.domain;
import com.Shlomi.englishapp.English_conversation_tutor.conversation.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.conversation.UserMessage;

public interface ChatModel {
    AIMessage getResponse(UserMessage message);
}
