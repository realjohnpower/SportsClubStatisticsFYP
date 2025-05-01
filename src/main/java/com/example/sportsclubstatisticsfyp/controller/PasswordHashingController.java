package com.example.sportsclubstatisticsfyp.controller;

import com.example.sportsclubstatisticsfyp.service.PasswordHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
// This is Alan Ryan's code that he provided me with for setting up Spring Security
// This controller class encrypts all passwords in the database using Bcrypt
// when a GET request is made to the controller
// Request only made once.
// If passwords aren't encrypted. users wouldn't be able to log in.
@Controller
public class PasswordHashingController {
    @Autowired
    private PasswordHashingService passwordHashingService;

    @GetMapping("/hash-passwords")
    @ResponseBody
    public String hashPasswords() {
        passwordHashingService.hashExistingPasswords();
        return "Passwords hashed successfully.";
    }
}
