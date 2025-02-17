package com.example.sportsclubstatisticsfyp.model.DTOForms;


import com.example.sportsclubstatisticsfyp.model.entities.TeamSessionStats;


import java.util.ArrayList;
import java.util.List;

public class TeamSessionStatsDTO {

    private List<TeamSessionStats> teamSessionStats=new ArrayList<TeamSessionStats>();

    public void addTeamSessionStats(TeamSessionStats teamSessionStats) {
        this.teamSessionStats.add(teamSessionStats);
    }

    public List<TeamSessionStats> getTeamSessionStats() {
        return teamSessionStats;
    }

    public void setTeamSessionStats(List<TeamSessionStats> teamSessionStats) {
        this.teamSessionStats = teamSessionStats;
    }
}
