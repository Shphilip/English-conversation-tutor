package com.Shlomi.englishapp.English_conversation_tutor.controller;

import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessageRequest;

// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

// import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
// import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
// import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;
// import com.Shlomi.englishapp.English_conversation_tutor.entities.handleRequestInterface;

// public interface ControllerInterface {
//     MessageInterface handleRequest(MessageInterface  message);
// }

public interface ControllerInterface {
    MessageInterface handleRequest(UserMessageRequest message);
}
