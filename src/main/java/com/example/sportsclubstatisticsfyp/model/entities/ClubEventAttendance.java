package com.example.sportsclubstatisticsfyp.model.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "club_event_attendance")
public class ClubEventAttendance implements Serializable {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "club_event_attendance_id")
        private Integer clubEventAttendanceId;



        @JoinColumn(name = "user_id", referencedColumnName = "user_id")
        @ManyToOne(optional = false)
        @ToString.Exclude
        private User clubMember;



        @JoinColumn(name = "club_event_id", referencedColumnName = "club_event_id")
        @ManyToOne(optional = false)
        private ClubEvents clubEvent;

        @Basic(optional = false)
        @Column(name = "attendance")
        private Boolean attendance;


    public User getClubMember() {
        return clubMember;
    }

    public void setClubMember(User clubMember) {
        this.clubMember = clubMember;
    }

    public Integer getClubEventAttendanceId() {
        return clubEventAttendanceId;
    }

    public void setClubEventAttendanceId(Integer clubEventAttendanceId) {
        this.clubEventAttendanceId = clubEventAttendanceId;
    }

    public ClubEvents getClubEvent() {
        return clubEvent;
    }

    public void setClubEvent(ClubEvents clubEvent) {
        this.clubEvent = clubEvent;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }
}
