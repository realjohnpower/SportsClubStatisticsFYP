package com.example.sportsclubstatisticsfyp.model.repositories;

import com.example.sportsclubstatisticsfyp.model.entities.Team;
import com.example.sportsclubstatisticsfyp.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.gender  = 'Male'")
    List<User> getAllMaleClubMembers();

    @Query("SELECT u FROM User u WHERE u.gender  = 'Female'")
    List<User> getAllFemaleClubMembers();






}