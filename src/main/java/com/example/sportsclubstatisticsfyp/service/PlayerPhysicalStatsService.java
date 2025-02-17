package com.example.sportsclubstatisticsfyp.service;

import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamEventDTOForm;
import com.example.sportsclubstatisticsfyp.model.DTOForms.TeamSessionStatsDTO;
import com.example.sportsclubstatisticsfyp.model.entities.*;
import com.example.sportsclubstatisticsfyp.model.repositories.PlayerPhysicalStatsRepository;
import com.example.sportsclubstatisticsfyp.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlayerPhysicalStatsService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlayerPhysicalStatsRepository playerPhysicalStatsRepository;

    public List <PlayerPhysicalStats> getPlayerPhysicalStatsById(Integer id){
       return playerPhysicalStatsRepository.findAllByUserId(id);

    }

    public PlayerPhysicalStats createPlayerPhysicalStats(PlayerPhysicalStats newPhysicalStats){

        Double playerHeight=newPhysicalStats.getHeightM();
        Double playerWeight= newPhysicalStats.getWeightKg();
        Double bmi= playerWeight/(playerHeight*playerHeight);
        newPhysicalStats.setBmi(bmi);
        newPhysicalStats.setDateRecorded(LocalDateTime.now());



        return playerPhysicalStatsRepository.save(newPhysicalStats);
    }









}
