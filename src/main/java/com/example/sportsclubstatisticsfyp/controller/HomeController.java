package com.example.sportsclubstatisticsfyp.controller;

import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.model.repositories.UserRepository;
import com.example.sportsclubstatisticsfyp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView index() {

        User clubMember = userService.getUserByEmail(User.getLoggedInEmail());

        return new ModelAndView("index", "member", clubMember );

    }
}
