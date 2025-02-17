package com.example.sportsclubstatisticsfyp.model.repositories;


import com.example.sportsclubstatisticsfyp.model.entities.ClubEventAttendance;
import com.example.sportsclubstatisticsfyp.model.entities.ClubEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubEventAttendanceRepository extends JpaRepository<ClubEventAttendance, Integer> {

}
