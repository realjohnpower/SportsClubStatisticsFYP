package com.example.sportsclubstatisticsfyp.model.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "team_event_attendance")
public class TeamEventAttendance implements Serializable {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "team_event_attendance_id")
        private Integer teamEventAttendanceId;



        @JoinColumn(name = "user_id", referencedColumnName = "user_id")
        @ManyToOne(optional = false)
        @ToString.Exclude
        private User teamMember;



        @JoinColumn(name = "team_event_id", referencedColumnName = "team_event_id")
        @ManyToOne(optional = false)
        private TeamEvent teamEvent;

        @Basic(optional = false)
        @Column(name = "attendance")
        private Boolean attendance;


    public Integer getTeamEventAttendanceId() {
        return teamEventAttendanceId;
    }

    public void setTeamEventAttendanceId(Integer teamEventAttendanceId) {
        this.teamEventAttendanceId = teamEventAttendanceId;
    }

    public User getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(User teamMember) {
        this.teamMember = teamMember;
    }

    public TeamEvent getTeamEvent() {
        return teamEvent;
    }

    public void setTeamEvent(TeamEvent teamEvent) {
        this.teamEvent = teamEvent;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }
}
