package com.example.sportsclubstatisticsfyp.model.entities;

import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "team_events")
public class TeamEvent implements Serializable {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "team_event_id")
        private Integer teamEventId;

        @Basic(optional = false)
        @Column(name = "event_name")
        private String eventName;


        @Basic(optional = false)
        @Column(name = "event_description")
        @Lob
        private String eventDescription;

        @Basic(optional = false)
        @Column(name = "start_date")
        @Temporal(TemporalType.TIMESTAMP)
        @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
        private LocalDateTime startDate;


        @JoinColumn(name = "team_id", referencedColumnName = "team_id")
        @ManyToOne(optional = false)
        private Team team;

        @Basic(optional = false)
        @Column(name = "location")
        @Lob
        private String location;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamEvent")
        private List<TeamEventAttendance> attendanceList;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamSession")
        @ToString.Exclude
        private List<TeamSessionStats> listOfPlayerSessionsStats;

        public Integer getTeamEventId() {
                return teamEventId;
        }

        public void setTeamEventId(Integer teamEventId) {
                this.teamEventId = teamEventId;
        }

        public String getEventName() {
                return eventName;
        }

        public void setEventName(String eventName) {
                this.eventName = eventName;
        }

        public String getEventDescription() {
                return eventDescription;
        }

        public void setEventDescription(String eventDescription) {
                this.eventDescription = eventDescription;
        }

        public LocalDateTime getStartDate() {
                return startDate;
        }

        public void setStartDate(LocalDateTime startDate) {
                this.startDate = startDate;
        }

        public Team getTeam() {
                return team;
        }

        public void setTeam(Team team) {
                this.team = team;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public List<TeamEventAttendance> getAttendanceList() {
                return attendanceList;
        }

        public void setAttendanceList(List<TeamEventAttendance> attendanceList) {
                this.attendanceList = attendanceList;
        }

        public List<TeamSessionStats> getListOfPlayerSessionsStats() {
                return listOfPlayerSessionsStats;
        }

        public void setListOfPlayerSessionsStats(List<TeamSessionStats> listOfPlayerSessionsStats) {
                this.listOfPlayerSessionsStats = listOfPlayerSessionsStats;
        }
}

