package com.example.sportsclubstatisticsfyp.model.entities;

import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team implements Serializable {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "team_id")
    private Integer team_Id;

    @Basic(optional = false)
    @Column(name = "team_name")

    private String teamName;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    @ToString.Exclude
    private User trainer;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "team_id"), //The foreign key column in the join table (user_roles) that refers to the Customer entity.
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> teamMembers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    @ToString.Exclude
    private Set<TeamEvent> listOfTeamEvents = new HashSet<>();


    public Integer getTeam_Id() {
        return team_Id;
    }

    public void setTeam_Id(Integer team_Id) {
        this.team_Id = team_Id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public Set<User> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<User> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Set<TeamEvent> getListOfTeamEvents() {
        return listOfTeamEvents;
    }

    public void setListOfTeamEvents(Set<TeamEvent> listOfTeamEvents) {
        listOfTeamEvents = listOfTeamEvents;
    }
}

