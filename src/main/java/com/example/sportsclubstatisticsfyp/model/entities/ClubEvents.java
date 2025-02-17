package com.example.sportsclubstatisticsfyp.model.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "club_events")
public class ClubEvents implements Serializable {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "club_event_id")
        private Integer clubEventId;

        @Basic(optional = false)
        @Column(name = "club_event_name")

        private String clubEventName;

        @Basic(optional = false)
        @Column(name = "club_event_description")
        @Lob
        private String clubEventDescription;

        @Basic(optional = false)
        @Column(name = "start_date")
        @Temporal(TemporalType.TIMESTAMP)
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startDate;


        @Basic(optional = false)
        @Column(name = "location")
        @Lob
        private String location;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "clubEvent")
        private List<ClubEventAttendance> attendanceList;

        public Integer getClubEventId() {
                return clubEventId;
        }

        public void setClubEventId(Integer clubEventId) {
                this.clubEventId = clubEventId;
        }

        public String getClubEventName() {
                return clubEventName;
        }

        public void setClubEventName(String clubEventName) {
                this.clubEventName = clubEventName;
        }

        public String getClubEventDescription() {
                return clubEventDescription;
        }

        public void setClubEventDescription(String clubEventDescription) {
                this.clubEventDescription = clubEventDescription;
        }

        public LocalDateTime getStartDate() {
                return startDate;
        }

        public void setStartDate(LocalDateTime startDate) {
                this.startDate = startDate;
        }



        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public List<ClubEventAttendance> getAttendanceList() {
                return attendanceList;
        }

        public void setAttendanceList(List<ClubEventAttendance> attendanceList) {
                this.attendanceList = attendanceList;
        }
}

