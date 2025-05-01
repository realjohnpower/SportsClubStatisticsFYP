package com.example.sportsclubstatisticsfyp.service;


import com.example.sportsclubstatisticsfyp.model.entities.*;
import com.example.sportsclubstatisticsfyp.repositories.PlayerPhysicalStatsRepository;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.util.*;

@Service
public class PlayerPhysicalStatsService {



    @Autowired
    private PlayerPhysicalStatsRepository playerPhysicalStatsRepository;

    public List <PlayerPhysicalStats> getPlayerPhysicalStatsById(Integer id){
       return playerPhysicalStatsRepository.findAllByUserId(id);

    }

    public PlayerPhysicalStats createPlayerPhysicalStats(PlayerPhysicalStats newPhysicalStats){

        Double playerHeight=newPhysicalStats.getHeightM();
        Double playerWeight= newPhysicalStats.getWeightKg();
        // calculating BMI using BMI formula
        Double bmi= playerWeight/(playerHeight*playerHeight);
        newPhysicalStats.setBmi(bmi);
        newPhysicalStats.setDateRecorded(LocalDate.now());



        return playerPhysicalStatsRepository.save(newPhysicalStats);
    }

    public Map<String, List<Map<String,Object>>> returnPlayerPhysicalStatsinJson(List<PlayerPhysicalStats> listOfPlayerPhysicalStats, User player) throws JsonProcessingException {



        String playersFullName=player.getFirstName()+" "+player.getLastName();



        List<Map<String , Object>> heightStatsMap=new ArrayList<>();
        List<Map<String , Object>> weightStatsMap=new ArrayList<>();
        List<Map<String , Object>> bmiStatsMap=new ArrayList<>();
        List<Map<String , Object>> bodyFatPercentageStatsMap=new ArrayList<>();

        for(PlayerPhysicalStats physicalStats:listOfPlayerPhysicalStats){
            Map<String, Object> heightStats=new HashMap<>();
            heightStats.put("playerName",playersFullName );
            heightStats.put("date",physicalStats.getDateRecorded().toString().trim());
            heightStats.put("value",physicalStats.getHeightM());

            heightStatsMap.add(heightStats);

            Map<String, Object> weightStats=new HashMap<>();
            weightStats.put("playerName",playersFullName );
            weightStats.put("value",physicalStats.getWeightKg());
            weightStats.put("date",physicalStats.getDateRecorded().toString().trim());
            weightStatsMap.add(weightStats);

            Map<String, Object> bmiStats=new HashMap<>();
            bmiStats.put("playerName",playersFullName );
            bmiStats.put("value",physicalStats.getBmi());
            bmiStats.put("date",physicalStats.getDateRecorded().toString().trim());
            bmiStatsMap.add(bmiStats);

            Map<String, Object> bodyFatPercentageStats=new HashMap<>();
            bodyFatPercentageStats.put("playerName",playersFullName );
            bodyFatPercentageStats.put("value",physicalStats.getFatPercentage());
            bodyFatPercentageStats.put("date",physicalStats.getDateRecorded().toString().trim());
            bodyFatPercentageStatsMap.add(bodyFatPercentageStats);
        }
        Map<String, List<Map<String, Object>>> playerPhysicalStatsMap =new HashMap<>();
        playerPhysicalStatsMap.put("weightStats", weightStatsMap);
        playerPhysicalStatsMap.put("bmiStats", bmiStatsMap);
        playerPhysicalStatsMap.put("heightStats", heightStatsMap);
        playerPhysicalStatsMap.put("bodyFatPercentageStats", bodyFatPercentageStatsMap);


        return playerPhysicalStatsMap;


    }









}
