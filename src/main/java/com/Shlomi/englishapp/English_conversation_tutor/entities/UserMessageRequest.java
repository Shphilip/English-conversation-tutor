package com.Shlomi.englishapp.English_conversation_tutor.entities;

public class UserMessageRequest implements handleRequestInterface{
    private String username;
    private String password;
    private String message;

    // Default constructor for Jackson deserialization
    public UserMessageRequest() {
    }

    // Constructor with parameters
    public UserMessageRequest(String username, String password, String message) {
        this.username = username;
        this.password = password;
        this.message = message;
    }

    // Getters
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public String getPassword() { 
        return password;
    }

    @Override
    public String getMessage() {
        return message;
    }

    // Setters for Jackson deserialization
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
