package com.example.sportsclubstatisticsfyp.service;

import com.example.sportsclubstatisticsfyp.model.entities.Role;
import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.model.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles() {


        return roleRepository.findAll();
    }
}
