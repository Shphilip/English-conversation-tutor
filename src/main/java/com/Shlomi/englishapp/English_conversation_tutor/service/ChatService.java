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
    private static final String USER_NOT_FOUND = "User not found!";

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
    
    /**
     * Handles conversation summarization when user says "Stop Conversation"
     */
    private MessageInterface handleConversationSummarization(User user) {
        MessageInterface userMessage = new UserMessage();
        userMessage.setMessage("Please summerize the following conversation briefly in a few sentences:\n" + user.getFullConversationHistory());
        userMessage.setConversationHistory(user.getHistoryConversationSummary());
        
        MessageInterface response = this.aiWrapper.getResponse(userMessage);
        user.setHistoryConversationSummary(response.getMessage()); // Update conversation summary
        user.setFullConversationHistory(""); // Clear full conversation history after summarization
        this.userRepository.save(user); // Save updated user to database
        
        return response;
    }
    
    /**
     * Handles regular message processing with AI response and history updates
     */
    private MessageInterface handleRegularMessage(User user, String messageText) {
        MessageInterface userMessage = new UserMessage();
        userMessage.setMessage(messageText);
        userMessage.setConversationHistory(user.getHistoryConversationSummary());
        
        user.setFullConversationHistory(user.getFullConversationHistory() + "User: " + messageText + "\n"); // Append user message to full history
        MessageInterface response = this.aiWrapper.getResponse(userMessage);
        user.setFullConversationHistory(user.getFullConversationHistory() + "AI Model: " + response.getMessage() + "\n"); // Append AI response to full history
        
        this.userRepository.save(user); // Save updated user to database
        
        return response;
    }
    
    @Override
    public MessageInterface processMessage(handleRequestInterface request) {

        String username = request.getUsername();
        String password = request.getPassword();
        String messageText = request.getMessage();

        User user = this.userRepository.findByUsernameAndPassword(username, password); // Fetch user from database

        if(user != null){ // User found, proceed with processing the message
            // Check if user wants to stop and summarize the conversation
            if (messageText.equalsIgnoreCase("Stop Conversation")) {
                return handleConversationSummarization(user);
            }
            
            // Handle regular message processing
            return handleRegularMessage(user, messageText);
        }
        // Return a UserMessage with the error message while user is not found
        UserMessage errorMessage = new UserMessage();
        errorMessage.setMessage(USER_NOT_FOUND);
        errorMessage.setConversationHistory(""); // No history for error
        return errorMessage;
    }
}
 


