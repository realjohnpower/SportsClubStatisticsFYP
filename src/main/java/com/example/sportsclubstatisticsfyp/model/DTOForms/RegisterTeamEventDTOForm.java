package com.example.sportsclubstatisticsfyp.model.DTOForms;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class RegisterTeamEventDTOForm {

    private Integer teamEventId;

    private String eventName;

    private String eventDescription;

    private String location;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventStartDate;


    private Integer teamId;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDateTime eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
