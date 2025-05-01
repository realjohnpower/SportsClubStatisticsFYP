package com.example.sportsclubstatisticsfyp.service;

import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
// This is Alan Ryan's code that he provided me with for  setting up Spring Security
// This service class encrypts all passwords in the database using Bcrypt
// If passwords aren't encrypted. users wouldn't be able to log in.
@Service
public class PasswordHashingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void hashExistingPasswords() {
        //get all users
        List<User> members = userRepository.findAll();

        for (User member : members) {
            String plainTextPassword = member.getPassword();
            //Check if the password is already hashed (BCrypt hashes start with $2a$)
            if (!plainTextPassword.startsWith("$2a$")) {
                //Hash the plain text password
                String hashedPassword = passwordEncoder.encode(plainTextPassword);
                member.setPassword(hashedPassword);
                //Update the database with the hashed pwd
                userRepository.save(member);
            }
        }
    }
}
