package com.Shlomi.englishapp.English_conversation_tutor.domain;
import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;

public interface WrapperInterface {
    MessageInterface getResponse(MessageInterface userMessage); //The main function that handles AI model messages
}
