package com.example.sportsclubstatisticsfyp.model.entities;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Table(name="player_physical_stats")
public class PlayerPhysicalStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "player_physical_stats_id")
    private Integer playerPhysicalStatsId;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User player;

    @Basic(optional = false)
    @Column(name="weight_kg")
    private Double weightKg;

    @Basic(optional = false)
    @Column(name="height_m")
    private Double heightM;

    @Basic(optional = false)
    @Column(name="fat_percentage")
    private Double fatPercentage;

    @Basic(optional=false)
    @Column(name="bmi")
    private Double bmi;

    @Basic(optional = false)
    @Column(name="date_recorded")
    @DateTimeFormat(pattern="YYYY-MM-dd")

    private LocalDate dateRecorded;

    public Integer getPlayerPhysicalStatsId() {
        return playerPhysicalStatsId;
    }

    public void setPlayerPhysicalStatsId(Integer playerPhysicalStatsId) {
        this.playerPhysicalStatsId = playerPhysicalStatsId;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Double getHeightM() {
        return heightM;
    }

    public void setHeightM(Double heightM) {
        this.heightM = heightM;
    }

    public Double getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(Double fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }



    public LocalDate getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(LocalDate dateRecorded) {
        this.dateRecorded = dateRecorded;
    }
}
