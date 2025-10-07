package com.Shlomi.englishapp.English_conversation_tutor.service;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Shlomi.englishapp.English_conversation_tutor.entities.User;

public interface UserRepository extends JpaRepository<User, String> { //The option to search on the database table is from the Suser by the Strin keay type, username and password
    User findByUsernameAndPassword(String username, String password);
}