package com.example.sportsclubstatisticsfyp.model.repositories;

import com.example.sportsclubstatisticsfyp.model.entities.PlayerPhysicalStats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerPhysicalStatsRepository extends JpaRepository<PlayerPhysicalStats, Integer> {
    @Query("SELECT p FROM PlayerPhysicalStats p WHERE p.player.userId  = :user_Id")
    List<PlayerPhysicalStats> findAllByUserId(@Param("user_Id")Integer user_Id);
}
