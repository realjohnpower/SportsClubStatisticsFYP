package com.example.sportsclubstatisticsfyp.repositories;


import com.example.sportsclubstatisticsfyp.model.entities.ClubEvents;
import com.example.sportsclubstatisticsfyp.model.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Query("SELECT t FROM Team t WHERE t.trainer.userId  = :user_Id")
    List<Team> findAllByTrainer(@Param("user_Id")int user_Id);
}
