package com.example.sportsclubstatisticsfyp.service;


import com.example.sportsclubstatisticsfyp.model.entities.ClubEventAttendance;
import com.example.sportsclubstatisticsfyp.model.entities.ClubEvents;

import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.repositories.ClubEventAttendanceRepository;
import com.example.sportsclubstatisticsfyp.repositories.ClubEventsRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class ClubEventsService {
    @Autowired
    private ClubEventsRepository clubEventsRepository;
    @Autowired
    private ClubEventAttendanceRepository clubEventAttendanceRepository;
    @Autowired
    private UserService userService;


    public ClubEvents createClubEvent(ClubEvents newEvent){



        return clubEventsRepository.save(newEvent);
    }

    public List<ClubEvents> getAllClubEvents(){

        return clubEventsRepository.findAll();
    }

    public void removeClubEvent(ClubEvents clubEvent){
         clubEventsRepository.delete(clubEvent);
    }

    public ClubEvents getClubEventById(int id){
        Optional<ClubEvents> clubEvent = clubEventsRepository.findById(id);
        if(clubEvent.isPresent())
        {
            return clubEvent.get();
        }else {
            return null;
        }
    }

    public Integer getAttendingRecordCount(ClubEvents clubEvent, boolean attending){
        Integer attendingCount = 0;

        for(ClubEventAttendance attendance: clubEvent.getAttendanceList()){
            if(attendance.getAttendance()==attending){
                attendingCount++;
            }
        }
        return attendingCount;
    }

    public Integer getNotRecordedAttendanceCount(ClubEvents clubEvent){
        Integer attendanceRecordedCount = clubEvent.getAttendanceList().size();
        Integer totalUsers = userService.getAllUsers().size();
        Integer notRecordedCount = totalUsers-attendanceRecordedCount;

        return notRecordedCount;
    }

    public ClubEvents updateClubEvent(ClubEvents clubEvent){
        ClubEvents updatedClubEvent = new ClubEvents();

        updatedClubEvent.setClubEventId(clubEvent.getClubEventId());
        updatedClubEvent.setClubEventName(clubEvent.getClubEventName());
        updatedClubEvent.setClubEventDescription(clubEvent.getClubEventDescription());
        updatedClubEvent.setLocation(clubEvent.getLocation());
        updatedClubEvent.setStartDate(clubEvent.getStartDate());

        updatedClubEvent.setAttendanceList(clubEvent.getAttendanceList());

        return clubEventsRepository.save(updatedClubEvent);

    }

    public ClubEvents goingToClubEvent(ClubEvents clubEvent, User user, Boolean attending){

        List<ClubEventAttendance> listOfClubEventAttendances= clubEventAttendanceRepository.findAll();
        if(!listOfClubEventAttendances.isEmpty()) {
            for (ClubEventAttendance clubEventAttendance : listOfClubEventAttendances) {
                Integer clubAttendanceClubMemberId = clubEventAttendance.getClubMember().getUserId();
                Integer clubAttendanceClubEventId = clubEventAttendance.getClubEvent().getClubEventId();
                // Updating users attendance if they have already recorded their attendance.
                if (user.getUserId() == clubAttendanceClubMemberId && clubEvent.getClubEventId() == clubAttendanceClubEventId) {
                    clubEvent.getAttendanceList().remove(clubEventAttendance);
                    Optional<ClubEventAttendance> optionalAttendance = clubEventAttendanceRepository.findById(clubEventAttendance.getClubEventAttendanceId());
                    ClubEventAttendance recordedAttendance = optionalAttendance.stream().findFirst().orElse(null);
                    recordedAttendance.setAttendance(attending);
                    clubEvent.getAttendanceList().add(recordedAttendance);

                    return clubEventsRepository.save(clubEvent);
                }
            }
        }
        ClubEventAttendance clubEventAttendance = new ClubEventAttendance();
        clubEventAttendance.setClubEvent(clubEvent);
        clubEventAttendance.setAttendance(attending);
        clubEventAttendance.setClubMember(user);
        List<ClubEventAttendance> attendanceList=new ArrayList<>();
        attendanceList.add(clubEventAttendance);
        clubEvent.setAttendanceList(attendanceList);

        return clubEventsRepository.save(clubEvent);

    }




}