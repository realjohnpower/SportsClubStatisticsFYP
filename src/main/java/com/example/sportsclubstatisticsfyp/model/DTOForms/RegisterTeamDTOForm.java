package com.example.sportsclubstatisticsfyp.model.DTOForms;

import java.util.List;
import java.util.Set;

public class RegisterTeamDTOForm {

    private Integer teamId;


    private String teamName;



    private Integer trainerID;



    private Set<Integer> listOfTeamMembers;


    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(Integer trainerID) {
        this.trainerID = trainerID;
    }

    public Set<Integer> getListOfTeamMembers() {
        return listOfTeamMembers;
    }

    public void setListOfTeamMembers(Set<Integer> listOfTeamMembers) {
        this.listOfTeamMembers = listOfTeamMembers;
    }
}
