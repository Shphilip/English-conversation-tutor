package com.Shlomi.englishapp.English_conversation_tutor.entities;

public class AIMessage implements MessageInterface{
    private String massage;

    @Override
    public String getMessage() {
        return this.massage;
    }

    @Override
    public void setMessage(String massage) {
        this.massage = massage;
    }
    private String conversationHistory; //!!!!!!!!!!!!!!!!!!!!DO NOT NEED TO INITIALIZE IT!!!!!
    @Override
    public String getConversationHistory() {
        return conversationHistory;
    }   
    @Override
    public void setConversationHistory(String conversationHistory) {
        this.conversationHistory = conversationHistory;
    }
}
