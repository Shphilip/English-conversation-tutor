package com.Shlomi.englishapp.English_conversation_tutor.entities;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;


@Entity
@Table(name = "app_user")  // Use custom table name to avoid reserved word conflict
//Create a table named "app_user" in the database
//with columns: id, username, password, HistoryConversationSummary
public class User implements UserInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String HistoryConversationSummary;
    private String username;
    
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
  
    public String getHistoryConversationSummary() {
        return HistoryConversationSummary;
    }
    public void setHistoryConversationSummary(String historyConversationSummary) {
        HistoryConversationSummary = historyConversationSummary;
    }   

}
