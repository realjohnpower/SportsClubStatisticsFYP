package com.example.sportsclubstatisticsfyp.repositories;


import com.example.sportsclubstatisticsfyp.model.entities.Team;
import com.example.sportsclubstatisticsfyp.model.entities.TeamEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamEventRepository extends JpaRepository<TeamEvent, Integer> {

}
