package com.example.sportsclubstatisticsfyp.repositories;

import com.example.sportsclubstatisticsfyp.model.entities.TeamSessionStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamSessionStatsRepository extends JpaRepository<TeamSessionStats, Integer> {
}
