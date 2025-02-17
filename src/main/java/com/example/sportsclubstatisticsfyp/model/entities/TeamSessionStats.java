package com.example.sportsclubstatisticsfyp.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "team_session_stats")
public class TeamSessionStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "team_session_stats_id")
    private Integer teamSessionStatsId;

    @Basic(optional = false)
    @Column(name="avg_bpm")
    private Double averageBpm;

    @Basic(optional = false)
    @Column(name="resting_bpm")
    private Double restingBpm;

    @Basic(optional = false)
    @Column(name="max_bpm")
    private Double maxBpm;

    @Basic(optional = false)
    @Column(name="calories_burned")
    private Double caloriesBurned;




    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User player;

    @JoinColumn(name = "team_event_id", referencedColumnName = "team_event_id")
    @ManyToOne(optional = false)
    private TeamEvent teamSession;

    public Integer getTeamSessionStatsId() {
        return teamSessionStatsId;
    }

    public void setTeamSessionStatsId(Integer teamSessionStatsId) {
        this.teamSessionStatsId = teamSessionStatsId;
    }

    public Double getAverageBpm() {
        return averageBpm;
    }

    public void setAverageBpm(Double averageBpm) {
        this.averageBpm = averageBpm;
    }

    public Double getRestingBpm() {
        return restingBpm;
    }

    public void setRestingBpm(Double restingBpm) {
        this.restingBpm = restingBpm;
    }

    public Double getMaxBpm() {
        return maxBpm;
    }

    public void setMaxBpm(Double maxBpm) {
        this.maxBpm = maxBpm;
    }

    public Double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public TeamEvent getTeamSession() {
        return teamSession;
    }

    public void setTeamSession(TeamEvent teamSession) {
        this.teamSession = teamSession;
    }
}
