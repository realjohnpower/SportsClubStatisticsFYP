package com.example.sportsclubstatisticsfyp.model.repositories;


import com.example.sportsclubstatisticsfyp.model.entities.ClubEventAttendance;
import com.example.sportsclubstatisticsfyp.model.entities.TeamEventAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamEventAttendanceRepository extends JpaRepository<TeamEventAttendance, Integer> {

}
