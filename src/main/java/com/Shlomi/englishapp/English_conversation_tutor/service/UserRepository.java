package com.Shlomi.englishapp.English_conversation_tutor.service;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Shlomi.englishapp.English_conversation_tutor.entities.User;

public interface UserRepository extends JpaRepository<User, Long> { 
    User findByUsernameAndPassword(String username, String password);
}