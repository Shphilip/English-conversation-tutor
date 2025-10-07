package com.Shlomi.englishapp.English_conversation_tutor.entities;

public class UserMessage implements MessageInterface{
    private String message;
    private String conversationHistory;

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getConversationHistory() {
        return conversationHistory;
    }

    @Override
    public void setConversationHistory(String conversationHistory) {
        this.conversationHistory = conversationHistory;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
