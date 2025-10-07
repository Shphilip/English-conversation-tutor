package com.Shlomi.englishapp.English_conversation_tutor.service;
import com.Shlomi.englishapp.English_conversation_tutor.entities.User;
import org.springframework.stereotype.Service;

import com.Shlomi.englishapp.English_conversation_tutor.domain.AiWrapper;
import com.Shlomi.englishapp.English_conversation_tutor.entities.MessageInterface;
import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;
// import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;
import com.Shlomi.englishapp.English_conversation_tutor.entities.handleRequestInterface;


@Service
public class ChatService implements ChatServiceInterface{
    private final AiWrapper aiWrapper; 
    private final UserRepository userRepository;

    public ChatService(AiWrapper aiWrapper, UserRepository userRepository ){
        this.aiWrapper = aiWrapper;
        this.userRepository = userRepository;

        User newUser = new User();
        newUser.setUsername("s");
        newUser.setPassword("s");
        newUser.setHistoryConversationSummary("My name is Shlomi"); // Initialize with empty conversation history
        
        // Save to database
        this.userRepository.save(newUser);
    }
    
    @Override
    public MessageInterface processMessage(handleRequestInterface request) {

        String username = request.getUsername();
        String password = request.getPassword();
        String messageText = request.getMessage();

        User user = this.userRepository.findByUsernameAndPassword(username, password); // Fetch user from database

        if(user != null){ // User found, proceed with processing the message
            MessageInterface userMessage = new UserMessage();
            userMessage.setMessage(messageText);
            userMessage.setConversationHistory(user.getHistoryConversationSummary());
            return this.aiWrapper.getResponse(userMessage);
        }
        return null;
    }
}
 


