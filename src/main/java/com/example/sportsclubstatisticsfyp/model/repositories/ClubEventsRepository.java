package com.example.sportsclubstatisticsfyp.model.repositories;


import com.example.sportsclubstatisticsfyp.model.entities.ClubEvents;
import com.example.sportsclubstatisticsfyp.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubEventsRepository extends JpaRepository<ClubEvents, Integer> {

}
